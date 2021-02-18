import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPlaca, Placa } from 'app/shared/model/placa.model';
import { PlacaService } from './placa.service';
import { PlacaComponent } from './placa.component';
import { PlacaDetailComponent } from './placa-detail.component';
import { PlacaUpdateComponent } from './placa-update.component';

@Injectable({ providedIn: 'root' })
export class PlacaResolve implements Resolve<IPlaca> {
  constructor(private service: PlacaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPlaca> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((placa: HttpResponse<Placa>) => {
          if (placa.body) {
            return of(placa.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Placa());
  }
}

export const placaRoute: Routes = [
  {
    path: '',
    component: PlacaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.placa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PlacaDetailComponent,
    resolve: {
      placa: PlacaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.placa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PlacaUpdateComponent,
    resolve: {
      placa: PlacaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.placa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PlacaUpdateComponent,
    resolve: {
      placa: PlacaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.placa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
