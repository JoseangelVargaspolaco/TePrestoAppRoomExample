package ucne.edu.teprestoapp.data.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
            "Registros.db")
            .fallbackToDestructiveMigration()
            .build()
    }
//    @Singleton
//    @Provides
//    fun providesMoshi(): Moshi {
//        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//    }
//    @Singleton
//    @Provides
//    fun providesTePrestoWebApi(moshi: Moshi): TePrestoWebApi {
//        return Retrofit.Builder()
//            .baseUrl("")
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//            .create(TePrestoWebApi::class.java)
//    }
    @Singleton
    @Provides
    fun providesPersonaDao(db: RoomExampleDb) = db.personaDao

    @Singleton
    @Provides
    fun providesOcupacionDao(db: RoomExampleDb) = db.ocupacionDao
}