import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
    imports: [
        PRIMENG_IMPORTS,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [
    ],
    declarations: [],
    exports: [
        PRIMENG_IMPORTS,
        FormsModule,
        ReactiveFormsModule,
    ]
})
export class SharedModule { }
