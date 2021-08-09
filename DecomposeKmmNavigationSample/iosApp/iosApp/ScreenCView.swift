import SwiftUI
import shared

struct ScreenCView: View {
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, IScreenCChild>>
    
    init(_ component: IScreenC) {
        self.routerStates = ObservableValue(component.routerState)
    }
    
    var body: some View {
        let child = self.routerStates.value.activeChild.instance
        
        switch child {
            case let screenC1 as IScreenCChild.ScreenC1:
                ScreenC1View(screenC1.component)
                    
            case let screenC2 as IScreenCChild.ScreenC2:
                ScreenC2View(screenC2.component)
            
            default: EmptyView()
        }
    }
}
