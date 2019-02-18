package com.fy.retrofit;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class RequestConn_MembersInjector implements MembersInjector<RequestConn> {
  private final Provider<ApiService> mConnServiceProvider;

  public RequestConn_MembersInjector(Provider<ApiService> mConnServiceProvider) {
    assert mConnServiceProvider != null;
    this.mConnServiceProvider = mConnServiceProvider;
  }

  public static MembersInjector<RequestConn> create(Provider<ApiService> mConnServiceProvider) {
    return new RequestConn_MembersInjector(mConnServiceProvider);
  }

  @Override
  public void injectMembers(RequestConn instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mConnService = mConnServiceProvider.get();
  }

  public static void injectMConnService(
      RequestConn instance, Provider<ApiService> mConnServiceProvider) {
    instance.mConnService = mConnServiceProvider.get();
  }
}
