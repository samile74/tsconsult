import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SendDto } from './classes/SendDto';
import { analyzeAndValidateNgModules } from '@angular/compiler';
const optionRequete = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'mon-entete-personnalise': 'maValeur'
  })
};
@Injectable({
  providedIn: 'root'
})
export class NetworkServiceService {
  constructor(private http: HttpClient) { }

  public getAllDistinctBy(column: string, onData: Function, onError: Function) {
    this.http.get("http://127.0.0.1:8080/api/public/list?column=" + column).subscribe(
      data => onData(data),
      error => onError(error)
    );

  }

  insertEmploye(value: any, onData: Function, onError: Function) {

    let sdto: SendDto = new SendDto();
    try {
      this.populateSendDto(sdto, value);
      this.http.post("http://127.0.0.1:8080/api/public/add", sdto).subscribe(
        data => {
          if(data["error"])
            onError(new Error(data["error"]));
          else
            onData(data)
        },
        error => onError(error)
      );
    } catch (error) {
      onError(error);
    }
  }

  private populateSendDto(sdto: SendDto, value: any) {
      sdto.employe = value;
  }

}
