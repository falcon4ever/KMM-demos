import SwiftUI
import shared

struct ScreenB2View: View {
    private let component: IScreenB2
    
    init(_ component: IScreenB2) {
        self.component = component
    }
    
    var body: some View {
        Text("B2")
    }
}
