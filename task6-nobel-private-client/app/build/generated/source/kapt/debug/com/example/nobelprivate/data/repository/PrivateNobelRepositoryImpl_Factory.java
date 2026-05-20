package com.example.nobelprivate.data.repository;

import com.example.nobelprivate.data.remote.PrivateNobelApi;
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
public final class PrivateNobelRepositoryImpl_Factory implements Factory<PrivateNobelRepositoryImpl> {
  private final Provider<PrivateNobelApi> apiProvider;

  private PrivateNobelRepositoryImpl_Factory(Provider<PrivateNobelApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public PrivateNobelRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static PrivateNobelRepositoryImpl_Factory create(Provider<PrivateNobelApi> apiProvider) {
    return new PrivateNobelRepositoryImpl_Factory(apiProvider);
  }

  public static PrivateNobelRepositoryImpl newInstance(PrivateNobelApi api) {
    return new PrivateNobelRepositoryImpl(api);
  }
}
