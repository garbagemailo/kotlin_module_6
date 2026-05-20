package com.example.nobelprivate.presentation.list;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014\u00a8\u0006\u001a"}, d2 = {"Lcom/example/nobelprivate/presentation/list/LaureatesViewModel;", "Landroidx/lifecycle/ViewModel;", "getLaureatesUseCase", "Lcom/example/nobelprivate/domain/usecase/GetLaureatesUseCase;", "<init>", "(Lcom/example/nobelprivate/domain/usecase/GetLaureatesUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/nobelprivate/presentation/common/UiState;", "", "Lcom/example/nobelprivate/domain/model/LaureateListItem;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "yearFilter", "", "getYearFilter", "()Ljava/lang/String;", "setYearFilter", "(Ljava/lang/String;)V", "categoryFilter", "getCategoryFilter", "setCategoryFilter", "load", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LaureatesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.nobelprivate.domain.usecase.GetLaureatesUseCase getLaureatesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.nobelprivate.presentation.common.UiState<java.util.List<com.example.nobelprivate.domain.model.LaureateListItem>>> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.nobelprivate.presentation.common.UiState<java.util.List<com.example.nobelprivate.domain.model.LaureateListItem>>> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String yearFilter = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String categoryFilter = "";
    
    @javax.inject.Inject()
    public LaureatesViewModel(@org.jetbrains.annotations.NotNull()
    com.example.nobelprivate.domain.usecase.GetLaureatesUseCase getLaureatesUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.nobelprivate.presentation.common.UiState<java.util.List<com.example.nobelprivate.domain.model.LaureateListItem>>> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getYearFilter() {
        return null;
    }
    
    public final void setYearFilter(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategoryFilter() {
        return null;
    }
    
    public final void setCategoryFilter(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final void load() {
    }
}