package com.example.nobelprivate.di;

@dagger.Module()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0007\u00a8\u0006\f"}, d2 = {"Lcom/example/nobelprivate/di/AppModule;", "", "<init>", "()V", "provideHttpClient", "Lio/ktor/client/HttpClient;", "provideApi", "Lcom/example/nobelprivate/data/remote/PrivateNobelApi;", "client", "provideRepository", "Lcom/example/nobelprivate/domain/repository/PrivateNobelRepository;", "api", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.nobelprivate.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final io.ktor.client.HttpClient provideHttpClient() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.nobelprivate.data.remote.PrivateNobelApi provideApi(@org.jetbrains.annotations.NotNull()
    io.ktor.client.HttpClient client) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.nobelprivate.domain.repository.PrivateNobelRepository provideRepository(@org.jetbrains.annotations.NotNull()
    com.example.nobelprivate.data.remote.PrivateNobelApi api) {
        return null;
    }
}