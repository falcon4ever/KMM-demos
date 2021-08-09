import SwiftUI
import shared

struct ScreenA1View: View {
    private let component: IScreenA1
    
    init(_ component: IScreenA1) {
        self.component = component
    }
    
    var body: some View {
        Text("Screen A1")
        Button("Go to Screen A2") {
            component.navigateToA2Clicked()
        }.padding(10.0)
    }
}
