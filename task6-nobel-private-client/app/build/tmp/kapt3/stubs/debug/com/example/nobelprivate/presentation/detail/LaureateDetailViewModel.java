package com.example.nobelprivate.presentation.detail;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0012"}, d2 = {"Lcom/example/nobelprivate/presentation/detail/LaureateDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "getLaureateDetailUseCase", "Lcom/example/nobelprivate/domain/usecase/GetLaureateDetailUseCase;", "<init>", "(Lcom/example/nobelprivate/domain/usecase/GetLaureateDetailUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/nobelprivate/presentation/common/UiState;", "Lcom/example/nobelprivate/domain/model/LaureateDetail;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "load", "", "item", "Lcom/example/nobelprivate/domain/model/LaureateListItem;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LaureateDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.nobelprivate.domain.usecase.GetLaureateDetailUseCase getLaureateDetailUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.nobelprivate.presentation.common.UiState<com.example.nobelprivate.domain.model.LaureateDetail>> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.nobelprivate.presentation.common.UiState<com.example.nobelprivate.domain.model.LaureateDetail>> uiState = null;
    
    @javax.inject.Inject()
    public LaureateDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.example.nobelprivate.domain.usecase.GetLaureateDetailUseCase getLaureateDetailUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.nobelprivate.presentation.common.UiState<com.example.nobelprivate.domain.model.LaureateDetail>> getUiState() {
        return null;
    }
    
    public final void load(@org.jetbrains.annotations.NotNull()
    com.example.nobelprivate.domain.model.LaureateListItem item) {
    }
}