import SwiftUI
import shared

struct ScreenCView: View {
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, IScreenCChild>>
    
    private var selectedTab: Binding<Int>
    
    init(_ component: IScreenC, selectedTab: Binding<Int>) {
        self.routerStates = ObservableValue(component.routerState)
        self.selectedTab = selectedTab
    }
    
    var body: some View {
        let child = self.routerStates.value.activeChild.instance
        
        NavigationView {
            TabView(selection: selectedTab) {
                VStack {
                    
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
                    switch child {
                        case let screenC1 as IScreenCChild.ScreenC1:
                            ScreenC1View(screenC1.component)
            
                        case let screenC2 as IScreenCChild.ScreenC2:
                            ScreenC2View(screenC2.component)
                            
                       default:
                           EmptyView()
                    }
                }
                .tabItem {
                    Image(systemName: "message")
                    Text("C")
                }
                .tag(2)
            }
            .navigationBarTitle("Tab C", displayMode: .inline)
            .navigationBarItems(leading: Button(action: {
                switch child {
                    case let screenC2 as IScreenCChild.ScreenC2:
                        screenC2.component.onBackClicked()
                   default:
                        print("ignore")
                }
            }) {
                if child is IScreenCChild.ScreenC2 {
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
