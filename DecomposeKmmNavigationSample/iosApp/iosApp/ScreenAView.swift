import SwiftUI
import shared

struct ScreenAView: View {
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, IScreenAChild>>
    
    init(_ component: IScreenA) {
        self.routerStates = ObservableValue(component.routerState)
    }
    
    var body: some View {
        let child = self.routerStates.value.activeChild.instance
        switch child {
            case let screenA1 as IScreenAChild.ScreenA1:
                ScreenA1View(screenA1.component)

            case let screenA2 as IScreenAChild.ScreenA2:
                ScreenA2View(screenA2.component)
                
           default:
               EmptyView()
        }
    }
}
