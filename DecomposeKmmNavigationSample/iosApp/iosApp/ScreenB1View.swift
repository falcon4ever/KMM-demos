import SwiftUI
import shared

struct ScreenB1View: View {
    private let component: IScreenB1
    
    @ObservedObject
    private var _model: ObservableValue<IScreenB1Model>
    
    init(_ component: IScreenB1) {
        self.component = component
        self._model = ObservableValue(component.model)
    }
    
    var body: some View {
        let model = _model.value
        
        Text("Screen B1")
        Text("Magic number: \(model.magicNumber)")
        Button("Go to Screen B2 (and get a magic number)") {
            component.navigateToB2Clicked()
        }.padding(10.0)
    }
}
