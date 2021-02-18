import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRecebimento, Recebimento } from 'app/shared/model/recebimento.model';
import { RecebimentoService } from './recebimento.service';
import { RecebimentoComponent } from './recebimento.component';
import { RecebimentoDetailComponent } from './recebimento-detail.component';
import { RecebimentoUpdateComponent } from './recebimento-update.component';

@Injectable({ providedIn: 'root' })
export class RecebimentoResolve implements Resolve<IRecebimento> {
  constructor(private service: RecebimentoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRecebimento> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((recebimento: HttpResponse<Recebimento>) => {
          if (recebimento.body) {
            return of(recebimento.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Recebimento());
  }
}

export const recebimentoRoute: Routes = [
  {
    path: '',
    component: RecebimentoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.recebimento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RecebimentoDetailComponent,
    resolve: {
      recebimento: RecebimentoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.recebimento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RecebimentoUpdateComponent,
    resolve: {
      recebimento: RecebimentoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.recebimento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RecebimentoUpdateComponent,
    resolve: {
      recebimento: RecebimentoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.recebimento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
