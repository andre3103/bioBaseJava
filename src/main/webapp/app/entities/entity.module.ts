import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'gerencia-id',
        loadChildren: () => import('./gerencia-id/gerencia-id.module').then(m => m.BioBaseApplicationGerenciaIdModule),
      },
      {
        path: 'entrega',
        loadChildren: () => import('./entrega/entrega.module').then(m => m.BioBaseApplicationEntregaModule),
      },
      {
        path: 'recebimento',
        loadChildren: () => import('./recebimento/recebimento.module').then(m => m.BioBaseApplicationRecebimentoModule),
      },
      {
        path: 'voucher-info',
        loadChildren: () => import('./voucher-info/voucher-info.module').then(m => m.BioBaseApplicationVoucherInfoModule),
      },
      {
        path: 'taxonomy',
        loadChildren: () => import('./taxonomy/taxonomy.module').then(m => m.BioBaseApplicationTaxonomyModule),
      },
      {
        path: 'collection-data',
        loadChildren: () => import('./collection-data/collection-data.module').then(m => m.BioBaseApplicationCollectionDataModule),
      },
      {
        path: 'barcode',
        loadChildren: () => import('./barcode/barcode.module').then(m => m.BioBaseApplicationBarcodeModule),
      },
      {
        path: 'sequenciamento',
        loadChildren: () => import('./sequenciamento/sequenciamento.module').then(m => m.BioBaseApplicationSequenciamentoModule),
      },
      {
        path: 'placa',
        loadChildren: () => import('./placa/placa.module').then(m => m.BioBaseApplicationPlacaModule),
      },
      {
        path: 'trace-file',
        loadChildren: () => import('./trace-file/trace-file.module').then(m => m.BioBaseApplicationTraceFileModule),
      },
      {
        path: 'image-data',
        loadChildren: () => import('./image-data/image-data.module').then(m => m.BioBaseApplicationImageDataModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BioBaseApplicationEntityModule {}
