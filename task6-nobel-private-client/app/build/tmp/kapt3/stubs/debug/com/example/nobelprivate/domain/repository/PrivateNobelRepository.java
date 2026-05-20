package com.example.nobelprivate.domain.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u00a6@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e\u00c0\u0006\u0003"}, d2 = {"Lcom/example/nobelprivate/domain/repository/PrivateNobelRepository;", "", "getLaureates", "", "Lcom/example/nobelprivate/domain/model/LaureateListItem;", "year", "", "category", "", "(Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLaureateDetail", "Lcom/example/nobelprivate/domain/model/LaureateDetail;", "item", "(Lcom/example/nobelprivate/domain/model/LaureateListItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface PrivateNobelRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLaureates(@org.jetbrains.annotations.Nullable()
    java.lang.Integer year, @org.jetbrains.annotations.Nullable()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.nobelprivate.domain.model.LaureateListItem>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLaureateDetail(@org.jetbrains.annotations.NotNull()
    com.example.nobelprivate.domain.model.LaureateListItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.nobelprivate.domain.model.LaureateDetail> $completion);
}