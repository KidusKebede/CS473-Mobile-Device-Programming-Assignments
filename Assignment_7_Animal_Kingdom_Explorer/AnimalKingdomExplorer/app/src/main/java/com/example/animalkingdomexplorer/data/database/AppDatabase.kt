package com.example.animalkingdomexplorer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.animalkingdomexplorer.data.dao.AnimalDao
import com.example.animalkingdomexplorer.data.dao.SpeciesDao
import com.example.animalkingdomexplorer.data.model.Species
import com.example.animalkingdomexplorer.data.model.Animal
import kotlinx.coroutines.*


@Database(entities = [Animal::class, Species::class], version = 1)
abstract class AnimalKingdomDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao
    abstract fun speciesDao(): SpeciesDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalKingdomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AnimalKingdomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalKingdomDatabase::class.java,
                    "animal_kingdom_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(AnimalKingdomDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class AnimalKingdomDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.animalDao(), database.speciesDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(animalDao: AnimalDao, speciesDao: SpeciesDao) {

            withContext(Dispatchers.IO) {
                animalDao.deleteAll()
                speciesDao.deleteAll()

                val lion = Animal(name = "Lion", habitat = "Grasslands", diet = "Carnivore")
                val elephant = Species(name = "Elephant", description = "Some description")

                animalDao.insertAnimal(lion)
                speciesDao.insertSpecies(elephant)
            }
        }
    }
}


