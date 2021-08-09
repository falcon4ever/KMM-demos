import SwiftUI
import shared

struct MainView: View {
    
    private let component: IMain
    
    @ObservedObject
    private var _model: ObservableValue<IMainModel>
    
    init(_ component: IMain) {
        self.component = component
        self._model = ObservableValue(component.models)
    }
    
    var body: some View {
        let model = _model.value
        
        Image("ron")
            .resizable()
            .frame(width: 273.0, height: 364.0)
        
        Text("\"\(model.quote)\"")
            .padding(32.0)
        
        Button(action: {
            component.fetchRandomQuote()
        }) {
            Image(systemName: "arrow.clockwise.circle")
                .resizable()
                .frame(width: 48.0, height: 48.0)
        }.padding(10.0)
    }
}
