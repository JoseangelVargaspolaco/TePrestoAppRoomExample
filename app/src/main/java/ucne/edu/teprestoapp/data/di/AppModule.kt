package ucne.edu.teprestoapp.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ucne.edu.teprestoapp.data.local.RoomExampleDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesDatabase( @ApplicationContext context: Context ): RoomExampleDb{
        return Room.databaseBuilder(
            context,
            RoomExampleDb::class.java,
            "RoomExample.db")
            .fallbackToDestructiveMigration()
            .build()

    }

    @Singleton
    @Provides
    fun providesOcupacionDao(db: RoomExampleDb) = db.ocupacionDao
}