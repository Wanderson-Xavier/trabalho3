import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http"
import { Observable } from "rxjs";
import { Veiculo } from "./models/veiculo.model";

@Injectable({
    providedIn: 'root'
})

export class VeiculoService{
    private API = 'http://localhost:8080/api/v1/veiculos';
    constructor(private httpClient: HttpClient){
    }
    save(veiculo: Veiculo): Observable<Veiculo>{
        return this.httpClient.post<Veiculo>(this.API,veiculo);
    }
    listarVeiculos(): Observable<any>{
        return this.httpClient.get(this.API);
    }
}