import { Component, OnInit } from "@angular/core";
import { VeiculoService } from "../../veiculo.service";
import { Veiculo } from "../../models/veiculo.model";
import { FormBuilder, FormControl, FormGroup, Validators, } from '@angular/forms';


@Component({
    selector: 'tf-veiculo-create',
    templateUrl: './veiculo-create.component.html'
})
export class VeiculoCreateComponent implements OnInit{
    veiculoForm!: FormGroup;
    submitted = false;

    placa!: string;
    cor!: string;
    anoFabricacao!: number;
    anoModelo!: number;
    marca!: number;
    constructor(private veiculoService: VeiculoService, private formBuilder: FormBuilder) { }
    ngOnInit(): void {
        this.veiculoForm = this.formBuilder.group({
            placa: new FormControl,
            cor: new FormControl, 
            anoFabricacao: new FormControl,
            anoModelo: new FormControl,  
            marca: new FormControl
        });
    }
    save() {
        if(this.veiculoForm.valid){
            const veiculo = this.veiculoForm.getRawValue() as Veiculo;
            this.veiculoService.save(veiculo).subscribe(
                () => (this.submitted = true),
                (error) => console.log(error)
            );
        }
    }
    addVeiculoForm(){
        this.submitted = false;
        this.veiculoForm.reset();
    }
}
