package com.example.nobelprivate.presentation.detail;

import com.example.nobelprivate.domain.usecase.GetLaureateDetailUseCase;
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
public final class LaureateDetailViewModel_Factory implements Factory<LaureateDetailViewModel> {
  private final Provider<GetLaureateDetailUseCase> getLaureateDetailUseCaseProvider;

  private LaureateDetailViewModel_Factory(
      Provider<GetLaureateDetailUseCase> getLaureateDetailUseCaseProvider) {
    this.getLaureateDetailUseCaseProvider = getLaureateDetailUseCaseProvider;
  }

  @Override
  public LaureateDetailViewModel get() {
    return newInstance(getLaureateDetailUseCaseProvider.get());
  }

  public static LaureateDetailViewModel_Factory create(
      Provider<GetLaureateDetailUseCase> getLaureateDetailUseCaseProvider) {
    return new LaureateDetailViewModel_Factory(getLaureateDetailUseCaseProvider);
  }

  public static LaureateDetailViewModel newInstance(
      GetLaureateDetailUseCase getLaureateDetailUseCase) {
    return new LaureateDetailViewModel(getLaureateDetailUseCase);
  }
}
