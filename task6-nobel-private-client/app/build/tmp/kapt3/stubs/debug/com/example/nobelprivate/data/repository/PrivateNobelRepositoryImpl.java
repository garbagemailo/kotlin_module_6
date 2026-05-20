package com.example.nobelprivate.data.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J(\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/nobelprivate/data/repository/PrivateNobelRepositoryImpl;", "Lcom/example/nobelprivate/domain/repository/PrivateNobelRepository;", "api", "Lcom/example/nobelprivate/data/remote/PrivateNobelApi;", "<init>", "(Lcom/example/nobelprivate/data/remote/PrivateNobelApi;)V", "getLaureates", "", "Lcom/example/nobelprivate/domain/model/LaureateListItem;", "year", "", "category", "", "(Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLaureateDetail", "Lcom/example/nobelprivate/domain/model/LaureateDetail;", "item", "(Lcom/example/nobelprivate/domain/model/LaureateListItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class PrivateNobelRepositoryImpl implements com.example.nobelprivate.domain.repository.PrivateNobelRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.nobelprivate.data.remote.PrivateNobelApi api = null;
    
    @javax.inject.Inject()
    public PrivateNobelRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.nobelprivate.data.remote.PrivateNobelApi api) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getLaureates(@org.jetbrains.annotations.Nullable()
    java.lang.Integer year, @org.jetbrains.annotations.Nullable()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.nobelprivate.domain.model.LaureateListItem>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getLaureateDetail(@org.jetbrains.annotations.NotNull()
    com.example.nobelprivate.domain.model.LaureateListItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.nobelprivate.domain.model.LaureateDetail> $completion) {
        return null;
    }
}