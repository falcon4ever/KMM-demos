import SwiftUI
import shared

struct ContentView: View {

    @State
    private var componentHolder =
        ComponentHolder {
            
            RootComponent(componentContext: $0,
                          storeFactory: DefaultStoreFactory(),
                          quotesApi: SwansonQuotesApi())
        }

    
	var body: some View {
        RootView(componentHolder.component)
            .onAppear { LifecycleRegistryExtKt.resume(self.componentHolder.lifecycle) }
            .onDisappear { LifecycleRegistryExtKt.stop(self.componentHolder.lifecycle) }
	}
}
