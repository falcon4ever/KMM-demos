import SwiftUI
import shared

struct RootView: View {
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, IRootChild>>
    
    init(_ component: IRoot) {
        self.routerStates = ObservableValue(component.routerState)
    }
    
    var body: some View {
        let child = self.routerStates.value.activeChild.instance
        
        switch child {
            case let main as IRootChild.Main:
                MainView(main.component)
                
            default: EmptyView()
        }
    }
}
