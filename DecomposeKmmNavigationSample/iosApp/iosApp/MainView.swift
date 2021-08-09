import SwiftUI
import shared

struct MainView: View {
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, IMainChild>>
    
    private let component: IMain
    
    @ObservedObject
    private var _model: ObservableValue<IMainModel>
    
    init(_ component: IMain) {
        self.component = component
        self._model = ObservableValue(component.model)
        self.routerStates = ObservableValue(component.routerState)
    }
    
    var body: some View {
        let model = _model.value
        
        let child = self.routerStates.value.activeChild.instance
        
        let binding = Binding<Int>(get: {
            switch model.selectedTab {
                case IMainTab.a:
                    return 0
                case IMainTab.b:
                    return 1
                case IMainTab.c:
                    return 2
                default:
                    return 0
            }
        }) {
            newValue in
            switch newValue {
                case 0:
                    component.onTabClick(tab: IMainTab.a)
                case 1:
                    component.onTabClick(tab: IMainTab.b)
                case 2:
                    component.onTabClick(tab: IMainTab.c)
                default:
                    component.onTabClick(tab: IMainTab.a)
            }
        }
        
        NavigationView {
            TabView(selection: binding) {
                VStack {
                    switch child {
                       case let screenA as IMainChild.ScreenA:
                           ScreenAView(screenA.component)
                       default:
                           EmptyView()
                    }
                }
                .tabItem {
                    Image(systemName: "house.fill")
                    Text("A")
                }
                .tag(0)
                
                VStack {
                    switch child {
                        case let screenB as IMainChild.ScreenB:
                           ScreenBView(screenB.component)
                       default:
                           EmptyView()
                    }
                }
                .tabItem {
                    Image(systemName: "list.dash")
                    Text("B")
                }
                .tag(1)
                
                VStack {
                    switch child {
                       case let screenC as IMainChild.ScreenC:
                           ScreenCView(screenC.component)
                       default:
                           EmptyView()
                    }
                }.tabItem {
                    Image(systemName: "message")
                    Text("C")
                }
                .tag(2)
            }
            //.navigationBarTitle("Test", displayMode: .inline)
        }
    }
}
