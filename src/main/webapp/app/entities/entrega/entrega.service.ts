import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEntrega } from 'app/shared/model/entrega.model';

type EntityResponseType = HttpResponse<IEntrega>;
type EntityArrayResponseType = HttpResponse<IEntrega[]>;

@Injectable({ providedIn: 'root' })
export class EntregaService {
  public resourceUrl = SERVER_API_URL + 'api/entregas';

  constructor(protected http: HttpClient) {}

  create(entrega: IEntrega): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entrega);
    return this.http
      .post<IEntrega>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(entrega: IEntrega): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entrega);
    return this.http
      .put<IEntrega>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEntrega>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEntrega[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(entrega: IEntrega): IEntrega {
    const copy: IEntrega = Object.assign({}, entrega, {
      data: entrega.data && entrega.data.isValid() ? entrega.data.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.data = res.body.data ? moment(res.body.data) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((entrega: IEntrega) => {
        entrega.data = entrega.data ? moment(entrega.data) : undefined;
      });
    }
    return res;
  }
}
