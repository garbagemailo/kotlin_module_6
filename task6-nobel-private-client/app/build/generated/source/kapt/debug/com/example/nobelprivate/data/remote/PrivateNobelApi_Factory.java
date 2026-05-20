package com.example.nobelprivate.data.remote;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.ktor.client.HttpClient;
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
public final class PrivateNobelApi_Factory implements Factory<PrivateNobelApi> {
  private final Provider<HttpClient> clientProvider;

  private PrivateNobelApi_Factory(Provider<HttpClient> clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public PrivateNobelApi get() {
    return newInstance(clientProvider.get());
  }

  public static PrivateNobelApi_Factory create(Provider<HttpClient> clientProvider) {
    return new PrivateNobelApi_Factory(clientProvider);
  }

  public static PrivateNobelApi newInstance(HttpClient client) {
    return new PrivateNobelApi(client);
  }
}
