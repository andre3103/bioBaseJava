import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBarcode, Barcode } from 'app/shared/model/barcode.model';
import { BarcodeService } from './barcode.service';
import { BarcodeComponent } from './barcode.component';
import { BarcodeDetailComponent } from './barcode-detail.component';
import { BarcodeUpdateComponent } from './barcode-update.component';

@Injectable({ providedIn: 'root' })
export class BarcodeResolve implements Resolve<IBarcode> {
  constructor(private service: BarcodeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBarcode> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((barcode: HttpResponse<Barcode>) => {
          if (barcode.body) {
            return of(barcode.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Barcode());
  }
}

export const barcodeRoute: Routes = [
  {
    path: '',
    component: BarcodeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.barcode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BarcodeDetailComponent,
    resolve: {
      barcode: BarcodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.barcode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BarcodeUpdateComponent,
    resolve: {
      barcode: BarcodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.barcode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BarcodeUpdateComponent,
    resolve: {
      barcode: BarcodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.barcode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
