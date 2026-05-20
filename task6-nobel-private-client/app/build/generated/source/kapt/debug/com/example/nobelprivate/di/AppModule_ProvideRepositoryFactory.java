package com.example.nobelprivate.di;

import com.example.nobelprivate.data.remote.PrivateNobelApi;
import com.example.nobelprivate.domain.repository.PrivateNobelRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class AppModule_ProvideRepositoryFactory implements Factory<PrivateNobelRepository> {
  private final Provider<PrivateNobelApi> apiProvider;

  private AppModule_ProvideRepositoryFactory(Provider<PrivateNobelApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public PrivateNobelRepository get() {
    return provideRepository(apiProvider.get());
  }

  public static AppModule_ProvideRepositoryFactory create(Provider<PrivateNobelApi> apiProvider) {
    return new AppModule_ProvideRepositoryFactory(apiProvider);
  }

  public static PrivateNobelRepository provideRepository(PrivateNobelApi api) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideRepository(api));
  }
}
