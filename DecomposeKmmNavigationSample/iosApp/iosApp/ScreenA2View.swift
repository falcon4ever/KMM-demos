import SwiftUI
import shared

struct ScreenA2View: View {
    private let component: IScreenA2
    
    init(_ component: IScreenA2) {
        self.component = component
    }
    
    var body: some View {
        Text("Screen A2")
        Button("Go to Screen A1") {
            component.onCloseClicked()
        }.padding(10.0)
    }
}
