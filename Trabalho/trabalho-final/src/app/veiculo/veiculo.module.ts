import { VeiculoCreateComponent } from "./veiculo-create/component/veiculo-create.component";
import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";



@NgModule({
    imports:[
        CommonModule,
        FormsModule,
        ReactiveFormsModule
    ],
    declarations:[
        VeiculoCreateComponent
    ],
    exports:[
        VeiculoCreateComponent
    ],
})
export class VeiculoModule{}