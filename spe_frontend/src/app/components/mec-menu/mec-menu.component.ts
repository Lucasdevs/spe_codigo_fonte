import { Component } from '@angular/core';
import { trigger, state, style } from '@angular/animations';
import { AppComponent } from 'src/app/app.component';

const ANIMATE = {
  VISIBLE: 'visibleAnimated',
  HIDDEN: 'hiddenAnimated'
};

@Component({
  selector: 'app-mec-menu',
  templateUrl: './mec-menu.component.html',
  animations: [
    trigger('children', [
      state(ANIMATE.HIDDEN, style({
        height: '0px'
      })),
      state(ANIMATE.VISIBLE, style({
        height: '*'
      })),
      state('visible', style({
        height: '*'
      })),
      state('hidden', style({
        height: '0px'
      }))
    ])
  ]
})
export class MecMenuComponent {

  indexDropdownActive: number;

  constructor(public app: AppComponent) {
    this.indexDropdownActive = null;
  }

  activeDropdown(indexDropdown: number) {
    if (indexDropdown === this.indexDropdownActive) {
      this.indexDropdownActive = null;
      return;
    }

    this.indexDropdownActive = indexDropdown;
  }

  getAnimate(indexDropdown: number) {
    return this.isActiveDropdown(indexDropdown) ? ANIMATE.VISIBLE : ANIMATE.HIDDEN;
  }

  isActiveDropdown(indexDropdown: number) {
    return this.indexDropdownActive === indexDropdown;
  }

  disableDropdown() {
    this.indexDropdownActive = null;
  }

  isAnotherDropdown() {
    return this.indexDropdownActive === null;
  }

}
