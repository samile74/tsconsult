import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import {NgForm} from '@angular/forms';
import { SendDto } from './classes/SendDto';
import { NetworkServiceService } from './network-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'employe';

  @ViewChild('employeArray') divList:ElementRef;


  constructor(private service:NetworkServiceService, private renderer: Renderer2){
  }

  private showDatas(data:any[]){

    for (let child of this.divList.nativeElement.childNodes) {
      child.remove();
    }
    this.divList.nativeElement.innerHTML="";

    if(data && data.length>0){
      for(let i=0;i<data.length;i++){
        const p: HTMLParagraphElement = this.renderer.createElement('p');
        p.innerHTML=data[i].name+" "+data[i].firstname+" : "+data[i].adress;
        p.style.border='2px solid grey';
        
        this.divList.nativeElement.append(p);
      }
    }else{
      const p: HTMLParagraphElement = this.renderer.createElement('p');
      p.innerHTML="Nothing to show"
      this.divList.nativeElement.append(p);
    }

  }

  onSubmit(f: NgForm) {
    console.log(f.value);  // { first: '', last: '' }
    console.log(f.valid);  // false

    if(f.valid){
      this.service.insertEmploye(
        f.value,
        (data)=>{
          if(data["error"]) alert(data["error"])
          else alert("Employe has been added to database")
        },
        (error)=>{alert(error)}
      );
    }else{
      alert("Entry not valid")
    }

  }

  findBy(column:string){
    this.service.getAllDistinctBy(
      column,
      (data)=>{
        if(data["error"]) alert(data["error"])
        else this.showDatas(data)
      },
      (error)=>{alert(error)}
    );

  }

}
