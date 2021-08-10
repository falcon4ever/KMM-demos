import SwiftUI
import shared

struct ScreenBView: View {
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, IScreenBChild>>
    
    init(_ component: IScreenB) {
        self.routerStates = ObservableValue(component.routerState)
    }
    
    var body: some View {
        let child = self.routerStates.value.activeChild.instance
        switch child {
            case let screenB1 as IScreenBChild.ScreenB1:
                ScreenB1View(screenB1.component)

            case let screenB2 as IScreenBChild.ScreenB2:
                ScreenB2View(screenB2.component)
                
           default:
               EmptyView()
        }
    }
}
