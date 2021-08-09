import SwiftUI
import shared

struct ScreenBView: View {
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, IScreenBChild>>
    
    private var selectedTab: Binding<Int>
    
    init(_ component: IScreenB, selectedTab: Binding<Int>) {
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
                    switch child {
                        case let screenB1 as IScreenBChild.ScreenB1:
                            ScreenB1View(screenB1.component)
            
                        case let screenB2 as IScreenBChild.ScreenB2:
                            ScreenB2View(screenB2.component)
                            
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
                }
                .tabItem {
                    Image(systemName: "message")
                    Text("C")
                }
                .tag(2)
            }
            .navigationBarTitle("Tab B", displayMode: .inline)
            .navigationBarItems(leading: Button(action: {
                switch child {
                    case let screenB2 as IScreenBChild.ScreenB2:
                        screenB2.component.onBackClicked()
                   default:
                        print("ignore")
                }
            }) {
                if child is IScreenBChild.ScreenB2 {
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
