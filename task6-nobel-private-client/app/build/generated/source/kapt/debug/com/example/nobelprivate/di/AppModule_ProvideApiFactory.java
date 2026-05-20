package com.example.nobelprivate.di;

import com.example.nobelprivate.data.remote.PrivateNobelApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.ktor.client.HttpClient;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppModule_ProvideApiFactory implements Factory<PrivateNobelApi> {
  private final Provider<HttpClient> clientProvider;

  private AppModule_ProvideApiFactory(Provider<HttpClient> clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public PrivateNobelApi get() {
    return provideApi(clientProvider.get());
  }

  public static AppModule_ProvideApiFactory create(Provider<HttpClient> clientProvider) {
    return new AppModule_ProvideApiFactory(clientProvider);
  }

  public static PrivateNobelApi provideApi(HttpClient client) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideApi(client));
  }
}
