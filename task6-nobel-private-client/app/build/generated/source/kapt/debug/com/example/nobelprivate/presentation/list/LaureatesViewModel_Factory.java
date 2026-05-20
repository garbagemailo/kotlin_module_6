package com.example.nobelprivate.presentation.list;

import com.example.nobelprivate.domain.usecase.GetLaureatesUseCase;
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
public final class LaureatesViewModel_Factory implements Factory<LaureatesViewModel> {
  private final Provider<GetLaureatesUseCase> getLaureatesUseCaseProvider;

  private LaureatesViewModel_Factory(Provider<GetLaureatesUseCase> getLaureatesUseCaseProvider) {
    this.getLaureatesUseCaseProvider = getLaureatesUseCaseProvider;
  }

  @Override
  public LaureatesViewModel get() {
    return newInstance(getLaureatesUseCaseProvider.get());
  }

  public static LaureatesViewModel_Factory create(
      Provider<GetLaureatesUseCase> getLaureatesUseCaseProvider) {
    return new LaureatesViewModel_Factory(getLaureatesUseCaseProvider);
  }

  public static LaureatesViewModel newInstance(GetLaureatesUseCase getLaureatesUseCase) {
    return new LaureatesViewModel(getLaureatesUseCase);
  }
}
