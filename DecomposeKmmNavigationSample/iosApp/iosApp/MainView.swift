import SwiftUI
import shared

class BackButtonWrapper: ObservableObject {
    @Published
    var hasBackButton: Bool = false
}

struct MainView: View {

    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, IMainChild>>
    
    private let component: IMain
    
    @ObservedObject
    private var _model: ObservableValue<IMainModel>
    
    @StateObject
    var tabA = BackButtonWrapper()
    
    @StateObject
    var tabB = BackButtonWrapper()
    
    @StateObject
    var tabC = BackButtonWrapper()

    let tabNames: [String] = ["A", "B", "C"]
    
    enum Tab: Int {
        case A = 0
        case B
        case C
    }
    
    init(_ component: IMain) {
        self.component = component
        self._model = ObservableValue(component.model)
        self.routerStates = ObservableValue(component.routerState)
    }
    
    var body: some View {
        let model = _model.value
        let child = self.routerStates.value.activeChild.instance
        
        // We observe the selected tab from the model in the main component
        let selectedTab = Binding<Int>(get: {
            switch model.selectedTab {
                case IMainTab.a:
                    return Tab.A.rawValue
                case IMainTab.b:
                    return Tab.B.rawValue
                case IMainTab.c:
                    return Tab.C.rawValue
                default:
                    return Tab.A.rawValue
            }
        }) {
            newValue in
            switch newValue {
                case Tab.A.rawValue:
                    component.onTabClick(tab: IMainTab.a)
                case Tab.B.rawValue:
                    component.onTabClick(tab: IMainTab.b)
                case Tab.C.rawValue:
                    component.onTabClick(tab: IMainTab.c)
                default:
                    component.onTabClick(tab: IMainTab.a)
            }
        }
        
        // We use this since a lower component router has the state that we will observe
        let hasBackButton = Binding<Bool>(get: {
            switch model.selectedTab {
                case IMainTab.a:
                    return tabA.hasBackButton
                case IMainTab.b:
                    return tabB.hasBackButton
                case IMainTab.c:
                    return tabC.hasBackButton
                default:
                    return tabA.hasBackButton
            }
        },
        set: {
            newValue in
            let _ = newValue // Not used
        })
        
        NavigationView {
            TabView(selection: selectedTab) {
                VStack {
                    switch child {
                       case let screenA as IMainChild.ScreenA:
                        ScreenAView(screenA.component)
                            .onAppear() {
                                // Subscribe so we can start listening for router in the local component changes
                                screenA.component.routerState.subscribe { (routerState) in
                                    if let _ = routerState.activeChild.instance as? IScreenAChild.ScreenA2  {
                                        tabA.hasBackButton = true
                                    }
                                    else {
                                        tabA.hasBackButton = false
                                    }
                                }
                            }
                       default:
                           EmptyView()
                    }
                }
                .tabItem {
                    Image(systemName: "house.fill")
                    Text(tabNames[0])
                }
                .tag(Tab.A.rawValue)

                VStack {
                    switch child {
                        case let screenB as IMainChild.ScreenB:
                            ScreenBView(screenB.component)
                                .onAppear() {
                                    screenB.component.routerState.subscribe { (routerState) in
                                        if let _ = routerState.activeChild.instance as? IScreenBChild.ScreenB2  {
                                            tabB.hasBackButton = true
                                        }
                                        else {
                                            tabB.hasBackButton = false
                                        }
                                    }
                                }
                       default:
                           EmptyView()
                    }
                }
                .tabItem {
                    Image(systemName: "list.dash")
                    Text(tabNames[1])
                }
                .tag(Tab.B.rawValue)

                VStack {
                    switch child {
                       case let screenC as IMainChild.ScreenC:
                        ScreenCView(screenC.component)
                            .onAppear() {
                                screenC.component.routerState.subscribe { (routerState) in
                                    if let _ = routerState.activeChild.instance as? IScreenCChild.ScreenC2  {
                                        tabC.hasBackButton = true
                                    }
                                    else {
                                        tabC.hasBackButton = false
                                    }
                                }
                            }
                       default:
                           EmptyView()
                    }
                }.tabItem {
                    Image(systemName: "message")
                    Text(tabNames[2])
                }
                .tag(Tab.C.rawValue)
            }
            .navigationBarTitle(getTitle(selectedTab: selectedTab), displayMode: .inline)
            .navigationBarItems(leading: Button(action: {
                    backPressed()
                }) {
                    getNavBarView(hasBackButton: hasBackButton)
                }
            )
        }
    }
    
    // Adjust title based on selected tab
    func getTitle(selectedTab: Binding<Int>) -> String {
        switch selectedTab.wrappedValue {
            case Tab.A.rawValue:
                return "Tab \(tabNames[0])"
            case Tab.B.rawValue:
                return "Tab \(tabNames[1])"
            case Tab.C.rawValue:
                return "Tab \(tabNames[2])"
            default:
                return ""
        }
    }
    
    // Handle backpress action
    func backPressed() {
        let child = self.routerStates.value.activeChild.instance
        switch child {
            case let screenA as IMainChild.ScreenA:
                if let screenA2 = screenA.component.routerState.value.activeChild.instance as? IScreenAChild.ScreenA2  {
                    screenA2.component.onBackClicked()
                }
            case let screenB as IMainChild.ScreenB:
                if let screenB2 = screenB.component.routerState.value.activeChild.instance as? IScreenBChild.ScreenB2  {
                    screenB2.component.onBackClicked()
                }
            case let screenC as IMainChild.ScreenC:
                if let screenC2 = screenC.component.routerState.value.activeChild.instance as? IScreenCChild.ScreenC2  {
                    screenC2.component.onBackClicked()
                }
            default:
                print("ignore press")
        }
    }
    
    // Show back button if needed
    @ViewBuilder
    func getNavBarView(hasBackButton: Binding<Bool>) -> some View {
        if (hasBackButton.wrappedValue) {
            HStack {
                Image(systemName: "chevron.left")
                Text("Back")
            }
        }
        else {
            EmptyView()
        }
    }
}
