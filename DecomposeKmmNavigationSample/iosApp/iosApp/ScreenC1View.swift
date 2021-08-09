import SwiftUI
import shared

struct ScreenC1View: View {
    private let component: IScreenC1
    
    @ObservedObject
    private var _model: ObservableValue<IScreenC1Model>
    
    init(_ component: IScreenC1) {
        self.component = component
        self._model = ObservableValue(component.model)
    }
    
    var body: some View {
        let model = _model.value
        
        Text("Screen C1")
        Text("Magic number: \(model.magicNumber)")
        Button("Go to Screen C2 (and get a magic number)") {
            component.navigateToC2Clicked()
        }.padding(10.0)
    }
}
