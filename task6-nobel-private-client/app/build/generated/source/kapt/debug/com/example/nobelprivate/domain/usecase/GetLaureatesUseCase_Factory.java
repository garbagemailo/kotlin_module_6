package com.example.nobelprivate.domain.usecase;

import com.example.nobelprivate.domain.repository.PrivateNobelRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class GetLaureatesUseCase_Factory implements Factory<GetLaureatesUseCase> {
  private final Provider<PrivateNobelRepository> repositoryProvider;

  private GetLaureatesUseCase_Factory(Provider<PrivateNobelRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetLaureatesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetLaureatesUseCase_Factory create(
      Provider<PrivateNobelRepository> repositoryProvider) {
    return new GetLaureatesUseCase_Factory(repositoryProvider);
  }

  public static GetLaureatesUseCase newInstance(PrivateNobelRepository repository) {
    return new GetLaureatesUseCase(repository);
  }
}
