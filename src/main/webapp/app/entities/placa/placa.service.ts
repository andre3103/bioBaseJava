import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPlaca } from 'app/shared/model/placa.model';

type EntityResponseType = HttpResponse<IPlaca>;
type EntityArrayResponseType = HttpResponse<IPlaca[]>;

@Injectable({ providedIn: 'root' })
export class PlacaService {
  public resourceUrl = SERVER_API_URL + 'api/placas';

  constructor(protected http: HttpClient) {}

  create(placa: IPlaca): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(placa);
    return this.http
      .post<IPlaca>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(placa: IPlaca): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(placa);
    return this.http
      .put<IPlaca>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPlaca>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPlaca[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(placa: IPlaca): IPlaca {
    const copy: IPlaca = Object.assign({}, placa, {
      dataMontagem: placa.dataMontagem && placa.dataMontagem.isValid() ? placa.dataMontagem.format(DATE_FORMAT) : undefined,
      data: placa.data && placa.data.isValid() ? placa.data.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataMontagem = res.body.dataMontagem ? moment(res.body.dataMontagem) : undefined;
      res.body.data = res.body.data ? moment(res.body.data) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((placa: IPlaca) => {
        placa.dataMontagem = placa.dataMontagem ? moment(placa.dataMontagem) : undefined;
        placa.data = placa.data ? moment(placa.data) : undefined;
      });
    }
    return res;
  }
}
