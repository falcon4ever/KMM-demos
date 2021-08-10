import SwiftUI
import shared

struct ScreenC2View: View {
    private let component: IScreenC2
    
    init(_ component: IScreenC2) {
        self.component = component
    }
    
    var body: some View {
        Text("Screen C2")
    }
}
