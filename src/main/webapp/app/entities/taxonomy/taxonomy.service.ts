import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITaxonomy } from 'app/shared/model/taxonomy.model';

type EntityResponseType = HttpResponse<ITaxonomy>;
type EntityArrayResponseType = HttpResponse<ITaxonomy[]>;

@Injectable({ providedIn: 'root' })
export class TaxonomyService {
  public resourceUrl = SERVER_API_URL + 'api/taxonomies';

  constructor(protected http: HttpClient) {}

  create(taxonomy: ITaxonomy): Observable<EntityResponseType> {
    return this.http.post<ITaxonomy>(this.resourceUrl, taxonomy, { observe: 'response' });
  }

  update(taxonomy: ITaxonomy): Observable<EntityResponseType> {
    return this.http.put<ITaxonomy>(this.resourceUrl, taxonomy, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITaxonomy>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITaxonomy[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
