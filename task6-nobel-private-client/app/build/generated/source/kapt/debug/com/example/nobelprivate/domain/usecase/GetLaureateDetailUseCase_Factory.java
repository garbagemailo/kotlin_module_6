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
public final class GetLaureateDetailUseCase_Factory implements Factory<GetLaureateDetailUseCase> {
  private final Provider<PrivateNobelRepository> repositoryProvider;

  private GetLaureateDetailUseCase_Factory(Provider<PrivateNobelRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetLaureateDetailUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetLaureateDetailUseCase_Factory create(
      Provider<PrivateNobelRepository> repositoryProvider) {
    return new GetLaureateDetailUseCase_Factory(repositoryProvider);
  }

  public static GetLaureateDetailUseCase newInstance(PrivateNobelRepository repository) {
    return new GetLaureateDetailUseCase(repository);
  }
}
