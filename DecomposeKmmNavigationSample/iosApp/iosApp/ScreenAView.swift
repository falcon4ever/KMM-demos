import SwiftUI
import shared

struct ScreenAView: View {
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, IScreenAChild>>
    
    private var selectedTab: Binding<Int>
    
    init(_ component: IScreenA, selectedTab: Binding<Int>) {
        self.routerStates = ObservableValue(component.routerState)
        self.selectedTab = selectedTab
    }
    
    var body: some View {
        let child = self.routerStates.value.activeChild.instance

        NavigationView {
            TabView(selection: selectedTab) {
                VStack {
                    switch child {
                        case let screenA1 as IScreenAChild.ScreenA1:
                            ScreenA1View(screenA1.component)
            
                        case let screenA2 as IScreenAChild.ScreenA2:
                            ScreenA2View(screenA2.component)
                            
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
                }
                .tabItem {
                    Image(systemName: "list.dash")
                    Text("B")
                }
                .tag(1)
                
                
                VStack {
                }
                .tabItem {
                    Image(systemName: "message")
                    Text("C")
                }
                .tag(2)
            }
            .navigationBarTitle("Tab A", displayMode: .inline)
            .navigationBarItems(leading: Button(action: {
                switch child {
                    case let screenA2 as IScreenAChild.ScreenA2:
                        screenA2.component.onBackClicked()
                   default:
                        print("ignore")
                }
            }) {
                if child is IScreenAChild.ScreenA2 {
                    HStack {
                        Image(systemName: "chevron.left")
                        Text("Back")
                    }
                }
                else {
                    EmptyView()
                }
            })
        }
    }
}
