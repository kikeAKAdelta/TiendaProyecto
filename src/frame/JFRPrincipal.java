
package frame;

import Clases.ControladorCompra;
import Clases.ControladorProducto;
import Clases.ControladorVenta;
import Clases.ErrorTienda;
import Clases.Producto;
import Clases.Proveedor;
import Clases.ControladorProveedor;
import Clases.DetalleVenta;
import Clases.Venta;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import Clases.Compra;
import Clases.DetalleCompra;
import Clases.Parametro;
import java.math.RoundingMode;
import net.sf.jcarrierpigeon.Notification;
import net.sf.jcarrierpigeon.NotificationQueue;
import net.sf.jcarrierpigeon.WindowPosition;
/**
 *
 * @author Jose Lopez Garcia
 */
public final class JFRPrincipal extends javax.swing.JFrame {

    boolean ventas, compras, productos, proveedores;
    boolean apagado, principal;
    int x,y;
    JTableHeader tHeadVentas,tHeadCompras,tHeadProductos,tHeadCompra,tHeadProveedores,tHeadDetalleCompra,tHeadBuscarVender;
    Producto obtenerProducto=null;
    DefaultTableModel modeloTablaVender;
    DefaultTableModel modeloProductos = new DefaultTableModel();
    DecimalFormat decimal = new DecimalFormat("0.00");
    DecimalFormat decimalProductos = new DecimalFormat("0.0000");
    private TableRowSorter trsFiltro;
    DefaultTableModel tablaModel= new DefaultTableModel();
    DefaultTableModel modeloProveedores= new DefaultTableModel();
    public String CodBarraVentas="";
    boolean exprod, encontrado, encontradoProv;
    Character s;
    int contandoClicksNombre=0,contandoClicksIneventario=0,contandoClicksCosto=0;
        
        
    
    public JFRPrincipal() {
        initComponents();
        tHeadVentas = tblProductosVender.getTableHeader();
        tHeadCompras=tblCompras.getTableHeader();
        tHeadProductos=tblProductos.getTableHeader();
        tHeadCompra=tblCompra.getTableHeader();
        tHeadProveedores=tblProveedores.getTableHeader();
        tHeadDetalleCompra=tblDetalleCompra.getTableHeader();
        tHeadBuscarVender=tblBuscarProductosVender.getTableHeader();
        modeloTablaVender = (DefaultTableModel) tblProductosVender.getModel();
        txtCantidadVender.setText("1");
        txtCodigoBarraVender.requestFocus();
        TableModel();
        cabezera();
        ventas = compras = productos = proveedores = apagado = false;
        btnVentas.setBorder(null);
        btnCompras.setBorder(null);
        btnProductos.setBorder(null);
        btnProveedores.setBorder(null);
        apagado2();
        Principal(true);
        Compras(false);
        Ventas(false);
        Productos(false);
        Proveedores(false);
        //disableBotonesProducto(false);
    }
    
    
    
    /*  ---- Color a las cabeceras de las tablas ----  */
    public void cabezera(){
        Font fuente = new Font("Tahoma", Font.BOLD, 12);
        tHeadVentas.setBackground(jpnBarraMenu.getBackground());
        tHeadVentas.setForeground(Color.WHITE);
        tHeadVentas.setFont(fuente);
        
        tHeadCompras.setBackground(jpnBarraMenu.getBackground());
        tHeadCompras.setForeground(Color.WHITE);
        tHeadCompras.setFont(fuente);
        
        tHeadProductos.setBackground(jpnBarraMenu.getBackground());
        tHeadProductos.setForeground(Color.WHITE);
        tHeadProductos.setFont(fuente);
        
        tHeadCompra.setBackground(jpnBarraMenu.getBackground());
        tHeadCompra.setForeground(Color.WHITE);
        tHeadCompra.setFont(fuente);
        
        tHeadProveedores.setBackground(jpnBarraMenu.getBackground());
        tHeadProveedores.setForeground(Color.WHITE);
        tHeadProveedores.setFont(fuente);
        
        tHeadDetalleCompra.setBackground(jpnBarraMenu.getBackground());
        tHeadDetalleCompra.setForeground(Color.WHITE);
        tHeadDetalleCompra.setFont(fuente);
        
        tHeadBuscarVender.setBackground(jpnBarraMenu.getBackground());
        tHeadBuscarVender.setForeground(Color.WHITE);
        tHeadBuscarVender.setFont(fuente);
        
    }
    public void mensajeNotificacion(String mensaje){
        frmNotificacion not = new frmNotificacion();
        not.setVisible(true);
        not.mensaje(mensaje);
    }
 
/*  ---- Visualización de imágenes en Menú ----  */
    public void Principal(boolean estado){
        if(!jpnProveedores.isVisible()){
        jpnPrimero.setVisible(estado);
        }else{
      }
    }
    public void Compras(boolean estado){
        if(!jpnProveedores.isVisible()){
        jpnSegundo.setVisible(estado);
        }else{
      }
    }
    public void Ventas(boolean estado){
        if(!jpnProveedores.isVisible()){
        jpnTercero.setVisible(estado);
        }else{
      }
    }
    public void Productos(boolean estado){
        if(!jpnProveedores.isVisible()){
        jpnCuarto.setVisible(estado);
        }else{
      }
    }
    public void Proveedores(boolean estado){
        if(!jpnProveedores.isVisible()){
        jpnQuinto.setVisible(estado);
        }else{
      }
    }
    public void apagado(){
        apagado = true;
        jpnPrincipal.setVisible(false);  
    }
    public void apagado2(){
        jpnProveedores.setVisible(false);
        jpnAgregarProv.setVisible(false);
        jpnModificarProveedor.setVisible(false);
        jpnVentas.setVisible(false);
        jpnProductos.setVisible(false);
        jpnNuevoProducto.setVisible(false);
        jpnRegistroCompra.setVisible(false);
        jpnCompras.setVisible(false);
        jpnDetalleCompra.setVisible(false);
        jpnModificarProducto.setVisible(false);
        pnlPortada.setVisible(false);
    }
    
    //------------------botones desactivados producto-------------
    public void disableBotonesProducto(boolean cambio){
        btnEliminarProducto.setEnabled(cambio);
        btnModificarProducto.setEnabled(cambio);
    }
    
    //-----------------limpiando cajas de texto-------------------------
    public void limpiandoTxtProducto(){
        txtCodBarraProductos.setText("");
        txtNombreProductos.setText("");
        txtInventarioProducto.setText("");
        txtCostoProductos.setText("");
        txtProductosBuscar.setText("");
        txtCodBarraProductos.requestFocus();
    }
    
    public void buscarProductoVender(){
        //COMPROBAR SI EL ESPACIO DE CODIGO DE BARRA ESTA VACIO
             if(txtCodigoBarraVender.getText().isEmpty()){
                 System.err.println("no hay codigooooooooooooooooooooo"+txtCodigoBarraVender.getText());
          JOptionPane.showMessageDialog(null, "Codigo de Barra no ingresado");
      }else{
                 Producto obtenerProducto=null;
                 try {
                     obtenerProducto = ControladorProducto.Obtener(txtCodigoBarraVender.getText());
                 } catch (ErrorTienda ex) {
                    
                 }
                 
                 //COMPROBAR RESULTADOS DE LA CONSULTA
                 if(obtenerProducto.getCodBarra().isEmpty()){
                     //JOptionPane.showMessageDialog(rootPane, "No se han encontrado resultados");
                     mensajeNotificacion("No se encontraron resultados");
                     
                 }else{
                     //ASIGNAR EL NOMBRE DEL PRODUCTO BUSCADO AL CAMPO ESPECIFICO
                     txtNombreProductoVender.setText(obtenerProducto.getNombre());
                     txtCantidadVender.requestFocus();
                     txtCantidadVender.selectAll();
                     
                 }
      }
    }
     public void AsignarCodBarra() throws ErrorTienda{
        int fila=0;
        try {
             fila = tblBuscarProductosVender.getSelectedRow();
            
            
        String codBarra = String.valueOf(modeloProductos.getValueAt(fila, 0));
        
       txtCodigoBarraVender.setText(codBarra);
        buscarProductoVender();
        
        } catch (Exception e) {
            throw new ErrorTienda("Errorrrrrrrrrrr", e.getMessage());
        }
        
        
    }
    
    //--------------------llenando tabla de productos----------------
    
    public void tablaBuscarProductos(){
        String codBarra=txtProductosBuscar.getText();
        DefaultTableModel modeloProductos = new DefaultTableModel();
        ArrayList<Producto> productos = new ArrayList();
        Object[] fila = new Object[4];
        if (codBarra.equals("")) {
            
            JOptionPane.showMessageDialog(null, "No ha introducido el codigo de barra o el nombre");
            
        }else{
            String[] campos = new String[] {"CodBarra", "Nombre", "Inventario", "Costo"};
            try {
                productos = ControladorProducto.Buscar(codBarra);
                modeloProductos.setColumnIdentifiers(campos);
                Iterator<Producto> prod = productos.iterator();
                while (prod.hasNext()) {
                    fila[0] = prod.next();
                    fila[1] = prod.next();
                    fila[2] = prod.next();
                    fila[3] = prod.next();
                    modeloProductos.addRow(fila);
                    tblProductos.setModel(modeloProductos);
                }
                if (tblProductos.getRowCount()==0) {
                    JOptionPane.showMessageDialog(null, "El producto buscado no existe");
                }
            } catch (ErrorTienda ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }
    
    
    public void tablaProductosVender(){
        //COMPROBAR QUE SE HAYA ELEGIDO UN PRODUCTO
        if(!txtNombreProductoVender.getText().isEmpty()){
            
                 try {
                     obtenerProducto = ControladorProducto.Obtener(txtCodigoBarraVender.getText());
                     int cantidad = Integer.parseInt(txtCantidadVender.getText());
                     int fila = modeloTablaVender.getRowCount();
                     //COMPARAR LA CANTIDAD DISPONIBLE DEL PRODUCTO CONTRA LA CANTIDAD DEMANDADA
                     
                     if(cantidad>obtenerProducto.getInventario()){
                         //JOptionPane.showMessageDialog(rootPane, "La cantidad demandada supera la cantidad disponible "+obtenerProducto.getInventario());
                         mensajeNotificacion("Cantidad disponible: "+obtenerProducto.getInventario());
                         txtCantidadVender.requestFocus();
                         txtCantidadVender.selectAll();
                     }else{
                         //AGREGAR A LA TABLA
                         
                         if(!verificarTablaProductosVender()){
                             if(Integer.parseInt(txtCantidadVender.getText())<=0){
                                //JOptionPane.showMessageDialog(rootPane, "Ingrese una cantidad válida");
                                 mensajeNotificacion("Ingrese una cantidad válida");
                             }else{
                              modeloTablaVender.addRow(new Object[]{"","","","",""});
                         modeloTablaVender.setValueAt(obtenerProducto.getCodBarra(), fila,0);
                         modeloTablaVender.setValueAt(obtenerProducto.getNombre(), fila,1);
                         modeloTablaVender.setValueAt(txtCantidadVender.getText(), fila,2);
                         double precioCalculado = DetalleVenta.CalcularPrecio(obtenerProducto);
                         System.out.println(precioCalculado);
                         System.out.println(decimal.format(precioCalculado));
                         modeloTablaVender.setValueAt(decimal.format(precioCalculado), fila,3);
                         modeloTablaVender.setValueAt(decimal.format(cantidad*precioCalculado), fila,4);
                         limpiarVenta();
                             }
                             
                         }else{
                             limpiarVenta();
                         }
                         
                     }
                            
                 } catch (ErrorTienda ex) {
                     
                 }
        }else{
            //JOptionPane.showMessageDialog(rootPane, "Producto no especificado");
            mensajeNotificacion("Producto no especificado");
            txtCodigoBarraVender.requestFocus();
            txtCodigoBarraVender.selectAll();
        }
        calcularTotal();
    }
    public boolean verificarTablaProductosVender(){
        //decimal.setRoundingMode(RoundingMode.CEILING); 
        int filas = modeloTablaVender.getRowCount();
       
        if(filas!=0){
            
           int iteracion =0,encontrado=-100;
           
        while( iteracion < filas){
            String codBarraBD = (String) modeloTablaVender.getValueAt(iteracion, 0);
            
            if(txtCodigoBarraVender.getText().equals(codBarraBD)){
                encontrado=iteracion;
            }
            iteracion++;
        }
        
           if(encontrado!=-100){
            if(Integer.parseInt(txtCantidadVender.getText())<=0){
              //JOptionPane.showMessageDialog(rootPane, "Ingrese una cantidad válida");
                mensajeNotificacion("Ingrese una cantidad válida");
            }else{
            int cantidadAntigua=Integer.parseInt(String.valueOf(modeloTablaVender.getValueAt(encontrado,2)));            
           int nuevaCantidad=(Integer.parseInt(txtCantidadVender.getText()));
           double nuevoSubTotal = ((nuevaCantidad+cantidadAntigua)*(Double.parseDouble(String.valueOf(modeloTablaVender.getValueAt(encontrado, 3)))));            
            int cantidadBase=obtenerProducto.getInventario();
            int cantidadTotal = nuevaCantidad+cantidadAntigua;
            
            if(cantidadTotal>cantidadBase){
            //JOptionPane.showMessageDialog(null, "La cantidad demanda supera la cantidad disponible "+cantidadBase);
                mensajeNotificacion("Cantidad disponible: "+cantidadBase);
            }else{
            modeloTablaVender.setValueAt((cantidadAntigua+nuevaCantidad), encontrado, 2);
            modeloTablaVender.setValueAt(decimal.format(nuevoSubTotal), encontrado, 4);    
            }
                
            }
           
            
            return true;
            
        }
              
        
        }
       return false;
        
    }
    public void calcularTotal(){
        //decimal.setRoundingMode(RoundingMode.CEILING); 
        int filas = modeloTablaVender.getRowCount();
        int iteraciones=0;
        double total=0;
        while(iteraciones<filas){
            total+=Double.parseDouble(String.valueOf(modeloTablaVender.getValueAt(iteraciones, 4)));
            iteraciones++;
        }
        double totalFinal=Double.parseDouble(decimal.format(total));
        txtTotalventa.setText("$ "+totalFinal);
    }

    public void limpiandoTxtProveedor(){
        txtIDProveedor.setText("");
        txtNombreProveedor.setText("");
        txtNIT.setText("");
        txtTelefonoProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtNombreProveedor.requestFocus();
        int idProv;
        try {
            idProv = ControladorProveedor.ObtenerIdProveedor();
            idProv = idProv+1;
            txtIDProveedor.setText(""+idProv);
        } catch (ErrorTienda ex) {
            Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //---------------------------Llenar tabla de proveedores----------------------------------------
        public void actualizarTablaProveedor(){
            modeloProveedores.setRowCount(0);
            
            ArrayList<Proveedor> listaProveedor=new ArrayList();
            Object fila[]=new Object[5];
            
        
            try {
            listaProveedor=ControladorProveedor.Obtener();
            String[] nombreProveedores = new String []{"IdProveedor","Nombre","Telefono","Direccion","NIT"};
            modeloProveedores.setColumnIdentifiers(nombreProveedores);
            Iterator<Proveedor> prov=listaProveedor.iterator();
                while(prov.hasNext()){
                    fila[0]= prov.next();
                    fila[1]= prov.next();
                    fila[2]= prov.next();
                    fila[3]= prov.next();
                    fila[4]= prov.next();
                    modeloProveedores.addRow(fila);
                    tblProveedores.setModel(modeloProveedores);
                }
            }
            
         catch (ErrorTienda ex) {
             Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
        
         }
    } 
        
        public void LlenarCompra(){
            cmbProveedor.removeAllItems();
    try {
            int idCompra;
            idCompra = ControladorCompra.ObtenerIdCompra();
            txtIdCompra.setText(String.valueOf(idCompra+1));
        //GENERAR FECHA 
            Date utilDate=new Date();
        SimpleDateFormat fecha= new SimpleDateFormat("dd'/'MM'/'YYYY");
            txtFecha.setText(fecha.format(utilDate)); 
         //AGREGAR PROVEEDORES AL COMBO BOX
            ArrayList<Proveedor> proveedor = new ArrayList<>();
             proveedor= ControladorProveedor.Obtener();
            Object vector[] = new Object[5];
            if (cmbProveedor.getItemCount()==0) {
                 Iterator<Proveedor>iterador=proveedor.iterator();
                    while(iterador.hasNext()){
                           vector[0]=iterador.next();
                            vector[1]=iterador.next();
                            vector[2]=iterador.next();
                            vector[3]=iterador.next();
                            vector[4]=iterador.next();
                            cmbProveedor.addItem(vector[1]);
          }
            }
         
        } catch (Exception e) {
        }

}
        
        
public void TableModel(){
String headers[] = {"Cod Barra","Producto","Cantidad","Costo","Subtotal"};
tablaModel.setColumnIdentifiers(headers);
tblCompra.setModel(tablaModel);
}
public void eliminarProductos(){
        int fila = tblProductosVender.getSelectedRow();
        if(fila!=-1){
        int opcion=JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea eliminar el producto seleccionado?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/iconos/pregunta.png"));
        if(opcion==0){
        modeloTablaVender.removeRow(tblProductosVender.getSelectedRow());
        calcularTotal();
        }    
        }
        
        
        
    }

public void eliminarCompras(){
    int fila = tblCompra.getSelectedRow();
    if (fila != -1) {
        int opcion1=JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea eliminar el producto seleccionado?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/iconos/pregunta.png"));
    
    if (opcion1 == 0) {
        tablaModel.removeRow(tblCompra.getSelectedRow());
        
    }}
}
public void guardarVenta() throws ErrorTienda, SQLException{
    Date utilDate=new Date();
        Clases.Venta vn;
        vn = new Venta();
         vn.setIdVenta(Integer.parseInt(txtIdVenta.getText()));
         vn.setFecha(utilDate);
         vn.setCliente(txtClienteVenta.getText());
         double total = Double.parseDouble(txtTotalventa.getText().substring(1));
         
         vn.setTotal(total);
         Clases.ControladorVenta cv = new ControladorVenta();
         
            Object [][] detallesVenta;
            
            int filas = modeloTablaVender.getRowCount();
            detallesVenta = new Object[filas][4];
            for(int x=0;x<filas;x++){
                detallesVenta[x][0]=Integer.parseInt(txtIdVenta.getText());
                detallesVenta[x][1]=modeloTablaVender.getValueAt(x, 0);
                detallesVenta[x][2]=Integer.parseInt(String.valueOf(modeloTablaVender.getValueAt(x, 2)));
                detallesVenta[x][3]=Double.parseDouble(String.valueOf(modeloTablaVender.getValueAt(x, 3)));
            }
            cv.Agregar(vn, detallesVenta);
            limpiarVenta();
            idVenta();
            modeloTablaVender.setNumRows(0);
            txtTotalventa.setText("$0");
            txtCodigoBarraVender.requestFocus();
}
public void limpiarVenta() throws ErrorTienda{
        txtCodigoBarraVender.setText("");
         txtNombreProductoVender.setText("");
         txtCantidadVender.setText("1");
         txtCodigoBarraVender.requestFocus();
         
    }

public void limpiarCompra() throws ErrorTienda{
        txtCodBarraProd.setText("");
         txtNomProd.setText("");
         txtCantidad.setText("1");
         txtCostoProd.setText("");
         txtCodBarraProd.requestFocus();   
    }

public void idVenta() throws ErrorTienda{
        
        txtIdVenta.setText(String.valueOf(ControladorVenta.ObtenerIdVenta()));
        
    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngFiltroProductos = new javax.swing.ButtonGroup();
        frmBUscarVentas = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        txtBuscarProductoVender = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBuscarProductosVender = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        btnBuscarAgregarVenta = new javax.swing.JButton();
        lblCerrarVentasBuscar = new javax.swing.JLabel();
        jpnBarraSuperior = new javax.swing.JPanel();
        lblBotonCerrar = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jpnBarraMenu = new javax.swing.JPanel();
        lblMenu = new javax.swing.JLabel();
        jpnSubMenu = new javax.swing.JPanel();
        btnCompras = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnHome = new javax.swing.JLabel();
        jpnCompras = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCompras = new javax.swing.JTable();
        btnAgregarCompra = new javax.swing.JButton();
        btnVerDetalle = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jSeparator27 = new javax.swing.JSeparator();
        lblProveedores3 = new javax.swing.JLabel();
        lblListadoCompras = new javax.swing.JLabel();
        jSeparator35 = new javax.swing.JSeparator();
        jpnPrincipal = new javax.swing.JPanel();
        jpnPrimero = new javax.swing.JPanel();
        lbl4 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lblMitad = new javax.swing.JLabel();
        pnlPortada = new javax.swing.JPanel();
        lbl8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        jpnSegundo = new javax.swing.JPanel();
        lbl11 = new javax.swing.JLabel();
        lbl12 = new javax.swing.JLabel();
        lbl13 = new javax.swing.JLabel();
        lbl14 = new javax.swing.JLabel();
        lbl15 = new javax.swing.JLabel();
        lblMitad2 = new javax.swing.JLabel();
        jpnTercero = new javax.swing.JPanel();
        lbl21 = new javax.swing.JLabel();
        lbl22 = new javax.swing.JLabel();
        lbl23 = new javax.swing.JLabel();
        lbl24 = new javax.swing.JLabel();
        lbl25 = new javax.swing.JLabel();
        lblMitad3 = new javax.swing.JLabel();
        jpnCuarto = new javax.swing.JPanel();
        lbl31 = new javax.swing.JLabel();
        lbl32 = new javax.swing.JLabel();
        lbl33 = new javax.swing.JLabel();
        lbl34 = new javax.swing.JLabel();
        lbl35 = new javax.swing.JLabel();
        lblMitad4 = new javax.swing.JLabel();
        jpnQuinto = new javax.swing.JPanel();
        lbl41 = new javax.swing.JLabel();
        lbl42 = new javax.swing.JLabel();
        lbl43 = new javax.swing.JLabel();
        lbl44 = new javax.swing.JLabel();
        lbl45 = new javax.swing.JLabel();
        lblMitad5 = new javax.swing.JLabel();
        jpnRegistroCompra = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        txtIdCompra = new javax.swing.JTextField();
        cmbProveedor = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCompra = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jPanel39 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lblFecha = new javax.swing.JLabel();
        lblIdCompra = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        lblProveedor = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        lblTotal = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        lblCodBarraProd = new javax.swing.JLabel();
        txtCodBarraProd = new javax.swing.JTextField();
        lblNomProd = new javax.swing.JLabel();
        txtNomProd = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator36 = new javax.swing.JSeparator();
        lblCostoProd = new javax.swing.JLabel();
        txtCostoProd = new javax.swing.JTextField();
        btnCancelarVenta1 = new javax.swing.JButton();
        jpnVentas = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductosVender = new javax.swing.JTable();
        btnVender = new javax.swing.JButton();
        btnBuscarProductoVenta = new javax.swing.JButton();
        txtTotalventa = new javax.swing.JTextField();
        txtNombreProductoVender = new javax.swing.JTextField();
        txtCantidadVender = new javax.swing.JTextField();
        btnAgregarProductoVenta = new javax.swing.JButton();
        jPanel44 = new javax.swing.JPanel();
        jSeparator15 = new javax.swing.JSeparator();
        lblProveedores5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtClienteVenta = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtIdVenta = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator23 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtCodigoBarraVender = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        btnCancelarVenta = new javax.swing.JButton();
        jpnProveedores = new javax.swing.JPanel();
        btnEliminarProveedor = new javax.swing.JButton();
        btnAgregarProveedor = new javax.swing.JButton();
        btnModificarProveedor = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProveedores = new javax.swing.JTable();
        jPanel42 = new javax.swing.JPanel();
        jSeparator20 = new javax.swing.JSeparator();
        lblProveedores6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        txtProveedoresBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator38 = new javax.swing.JSeparator();
        jpnAgregarProv = new javax.swing.JPanel();
        btnGuardarProveedor = new javax.swing.JButton();
        btnAtrasProveedores = new javax.swing.JButton();
        txtDireccionProveedor = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        jPanel45 = new javax.swing.JPanel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIDProveedor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        txtTelefonoProveedor = new javax.swing.JFormattedTextField();
        txtNIT = new javax.swing.JTextField();
        jpnModificarProveedor = new javax.swing.JPanel();
        btnGuardarModificarProveedor = new javax.swing.JButton();
        btnAtrasModificarProveedor = new javax.swing.JButton();
        txtNuevoDireccionProveedor = new javax.swing.JTextField();
        txtNuevoNombreProveedor = new javax.swing.JTextField();
        jPanel48 = new javax.swing.JPanel();
        jSeparator40 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtIDProveedor1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator41 = new javax.swing.JSeparator();
        jSeparator42 = new javax.swing.JSeparator();
        jSeparator43 = new javax.swing.JSeparator();
        txtNuevoTelefono = new javax.swing.JFormattedTextField();
        txtNuevoNit = new javax.swing.JTextField();
        jpnModificarProducto = new javax.swing.JPanel();
        btnGuardarModificarProveedor1 = new javax.swing.JButton();
        btnAtrasModificarProveedor1 = new javax.swing.JButton();
        txtNuevoInventarioProducto = new javax.swing.JTextField();
        jPanel49 = new javax.swing.JPanel();
        jSeparator44 = new javax.swing.JSeparator();
        jLabel40 = new javax.swing.JLabel();
        txtNuevoCodBarraProducto = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jSeparator45 = new javax.swing.JSeparator();
        jSeparator46 = new javax.swing.JSeparator();
        jSeparator47 = new javax.swing.JSeparator();
        jSeparator48 = new javax.swing.JSeparator();
        txtNuevoNombreProducto = new javax.swing.JTextField();
        txtNuevoCostoProducto = new javax.swing.JTextField();
        jpnProductos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnNuevoProducto = new javax.swing.JButton();
        btnBuscarProducto = new javax.swing.JButton();
        btnModificarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        jSeparator14 = new javax.swing.JSeparator();
        lblProveedores4 = new javax.swing.JLabel();
        jSeparator25 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        txtProductosBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator37 = new javax.swing.JSeparator();
        jpnNuevoProducto = new javax.swing.JPanel();
        btnAgregarNuevoProducto = new javax.swing.JButton();
        btnSalirProductos = new javax.swing.JButton();
        txtCodBarraProductos = new javax.swing.JTextField();
        txtNombreProductos = new javax.swing.JTextField();
        txtCostoProductos = new javax.swing.JTextField();
        jPanel46 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator26 = new javax.swing.JSeparator();
        jSeparator34 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtInventarioProducto = new javax.swing.JTextField();
        jSeparator39 = new javax.swing.JSeparator();
        jpnDetalleCompra = new javax.swing.JPanel();
        txtCodBarraProductos1 = new javax.swing.JTextField();
        txtNombreProductos1 = new javax.swing.JTextField();
        jPanel47 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jSeparator28 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jSeparator29 = new javax.swing.JSeparator();
        jSeparator30 = new javax.swing.JSeparator();
        jSeparator31 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblDetalleCompra = new javax.swing.JTable();
        jSeparator32 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        jSeparator33 = new javax.swing.JSeparator();
        txtPrecioProductos2 = new javax.swing.JTextField();
        txtTotal2 = new javax.swing.JTextField();
        btnAtrasDetalleCompra = new javax.swing.JButton();

        frmBUscarVentas.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        frmBUscarVentas.setTitle("Ventas");
        frmBUscarVentas.setAlwaysOnTop(true);
        frmBUscarVentas.setUndecorated(true);
        frmBUscarVentas.setSize(new java.awt.Dimension(740, 420));
        frmBUscarVentas.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarProductoVender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarProductoVenderKeyPressed(evt);
            }
        });
        jPanel1.add(txtBuscarProductoVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 350, -1));

        tblBuscarProductosVender.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Barra", "Nombre Producto", "Cantidad", "Precio Unitario $"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBuscarProductosVender.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblBuscarProductosVender);
        if (tblBuscarProductosVender.getColumnModel().getColumnCount() > 0) {
            tblBuscarProductosVender.getColumnModel().getColumn(0).setResizable(false);
            tblBuscarProductosVender.getColumnModel().getColumn(1).setResizable(false);
            tblBuscarProductosVender.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblBuscarProductosVender.getColumnModel().getColumn(2).setResizable(false);
            tblBuscarProductosVender.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 680, 180));

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Nombre Producto");
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        btnBuscarAgregarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/AgregarVentaBuscar.png"))); // NOI18N
        btnBuscarAgregarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarAgregarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAgregarVentaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarAgregarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 240, 40));

        lblCerrarVentasBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/exit32.png"))); // NOI18N
        lblCerrarVentasBuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblCerrarVentasBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrarVentasBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarVentasBuscarMouseClicked(evt);
            }
        });
        jPanel1.add(lblCerrarVentasBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, -1, 30));

        frmBUscarVentas.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 420));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iShop");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iconos/lanzador.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnBarraSuperior.setBackground(new java.awt.Color(0, 0, 0));
        jpnBarraSuperior.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jpnBarraSuperior.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpnBarraSuperiorMouseDragged(evt);
            }
        });
        jpnBarraSuperior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpnBarraSuperiorMousePressed(evt);
            }
        });
        jpnBarraSuperior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBotonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/exit32.png"))); // NOI18N
        lblBotonCerrar.setToolTipText("Salir");
        lblBotonCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBotonCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBotonCerrarMouseClicked(evt);
            }
        });
        jpnBarraSuperior.add(lblBotonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 30, 50));

        lblLogo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/lanzador.png"))); // NOI18N
        lblLogo.setText("iShop");
        lblLogo.setToolTipText("");
        jpnBarraSuperior.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 50));

        getContentPane().add(jpnBarraSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 55));

        jpnBarraMenu.setBackground(new java.awt.Color(102, 0, 0));
        jpnBarraMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMenu.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(255, 255, 255));
        lblMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/mas32.png"))); // NOI18N
        lblMenu.setText("Menu");
        jpnBarraMenu.add(lblMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 15, 90, 50));

        jpnSubMenu.setBackground(new java.awt.Color(102, 0, 0));
        jpnSubMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnSubMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/compras.png"))); // NOI18N
        btnCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnComprasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnComprasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnComprasMouseExited(evt);
            }
        });
        btnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprasActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 20, 180, 40));

        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/productos.png"))); // NOI18N
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProductosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProductosMouseExited(evt);
            }
        });
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 120, 180, 40));

        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ventas.png"))); // NOI18N
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVentasMouseExited(evt);
            }
        });
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        jpnSubMenu.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 70, 180, 40));

        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/proveedores.png"))); // NOI18N
        btnProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProveedoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProveedoresMouseExited(evt);
            }
        });
        jpnSubMenu.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(-126, 170, 180, 40));

        jpnBarraMenu.add(jpnSubMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 77, 190, 230));

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Home48.png"))); // NOI18N
        btnHome.setToolTipText("Inicio");
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });
        jpnBarraMenu.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, -1, -1));

        getContentPane().add(jpnBarraMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 600));

        jpnCompras.setName("jpnCompras"); // NOI18N
        jpnCompras.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCompras =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblCompras.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Compra 1", "Pollo Indio", "12/Febrero/17", "$23.00"},
                {"Compra 2", null, null, null},
                {"Compra 3", null, null, null},
                {"Compra 4", null, null, null}
            },
            new String [] {
                "Id Compra", "Proveedor", "Fecha", "Total"
            }
        ));
        tblCompras.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblCompras);

        jpnCompras.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 660, 310));

        btnAgregarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar.png"))); // NOI18N
        btnAgregarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarCompraMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarCompraMouseExited(evt);
            }
        });
        btnAgregarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCompraActionPerformed(evt);
            }
        });
        jpnCompras.add(btnAgregarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, 110, 30));

        btnVerDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/detalles2.png"))); // NOI18N
        btnVerDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerDetalleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerDetalleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerDetalleMouseExited(evt);
            }
        });
        jpnCompras.add(btnVerDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 110, 30));

        jPanel37.setBackground(new java.awt.Color(0, 0, 0));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator27.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel37.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, 50));

        lblProveedores3.setBackground(new java.awt.Color(255, 255, 255));
        lblProveedores3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblProveedores3.setForeground(new java.awt.Color(255, 255, 255));
        lblProveedores3.setText("Compras");
        jPanel37.add(lblProveedores3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 90, 30));

        jpnCompras.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 50));

        lblListadoCompras.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblListadoCompras.setText("Listado de Compras Realizadas:");
        jpnCompras.add(lblListadoCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 200, -1));
        jpnCompras.add(jSeparator35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 117, 200, 10));

        getContentPane().add(jpnCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 730, 600));

        jpnPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jpnPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnPrimero.setBackground(new java.awt.Color(0, 0, 0));
        jpnPrimero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl4.setForeground(new java.awt.Color(255, 255, 255));
        lbl4.setText("iShop");
        jpnPrimero.add(lbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 50, -1));

        lbl7.setBackground(new java.awt.Color(153, 153, 153));
        lbl7.setForeground(new java.awt.Color(102, 102, 102));
        lbl7.setText("Versión 1.0");
        lbl7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl7MouseExited(evt);
            }
        });
        jpnPrimero.add(lbl7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 570, 70, -1));

        lbl5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl5.setForeground(new java.awt.Color(102, 102, 102));
        lbl5.setText("Te damos la bienvenida a tu");
        jpnPrimero.add(lbl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        lbl6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl6.setForeground(new java.awt.Color(102, 102, 102));
        lbl6.setText("nuevo sistema de Tienda.");
        jpnPrimero.add(lbl6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, 20));

        lblMitad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mitad.jpg"))); // NOI18N
        jpnPrimero.add(lblMitad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 600));

        pnlPortada.setBackground(new java.awt.Color(0, 0, 0));
        pnlPortada.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Desarrolladores", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlPortada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/config48.png"))); // NOI18N
        pnlPortada.add(lbl8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 50, 50));

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Gil Menjívar, Sergio Daniel");
        pnlPortada.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 250, -1));

        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Alegría Arévalo, Ismael Enrique");
        pnlPortada.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 250, -1));

        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Baños Lobos, Pedro Javier");
        pnlPortada.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, -1));

        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("Barrientos Hernández, Ricardo Alberto");
        pnlPortada.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 250, -1));

        jLabel38.setForeground(new java.awt.Color(102, 102, 102));
        jLabel38.setText("García López, José Armando");
        pnlPortada.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 250, -1));

        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setText("García Rodríguez, Oscar Arnoldo");
        pnlPortada.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 250, -1));

        jpnPrimero.add(pnlPortada, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 400, 350, 160));

        lbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_e776(0)_64.png"))); // NOI18N
        jpnPrimero.add(lbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, -1, -1));

        jpnPrincipal.add(jpnPrimero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1070, 600));

        jpnSegundo.setBackground(new java.awt.Color(0, 0, 0));
        jpnSegundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl11.setForeground(new java.awt.Color(255, 255, 255));
        lbl11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_e73d(0)_32.png"))); // NOI18N
        lbl11.setText("Compras");
        jpnSegundo.add(lbl11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 120, -1));

        lbl12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl12.setForeground(new java.awt.Color(102, 102, 102));
        lbl12.setText("Podrás realizar compras y ");
        jpnSegundo.add(lbl12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        lbl13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl13.setForeground(new java.awt.Color(102, 102, 102));
        lbl13.setText("abastecer tu Tienda.");
        jpnSegundo.add(lbl13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, 30));

        lbl14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl14.setForeground(new java.awt.Color(102, 102, 102));
        lbl14.setText("Usa esta opción para manejar");
        jpnSegundo.add(lbl14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 190, -1));

        lbl15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl15.setForeground(new java.awt.Color(102, 102, 102));
        lbl15.setText("el sistema de Compras.");
        jpnSegundo.add(lbl15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, 30));

        lblMitad2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mitad2.jpg"))); // NOI18N
        jpnSegundo.add(lblMitad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 370, 600));

        jpnPrincipal.add(jpnSegundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 730, 600));

        jpnTercero.setBackground(new java.awt.Color(0, 0, 0));
        jpnTercero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl21.setForeground(new java.awt.Color(255, 255, 255));
        lbl21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_e789(2)_32.png"))); // NOI18N
        lbl21.setText("Ventas");
        jpnTercero.add(lbl21, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 120, -1));

        lbl22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl22.setForeground(new java.awt.Color(102, 102, 102));
        lbl22.setText("Podrás manejar los ingresos");
        jpnTercero.add(lbl22, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, -1, -1));

        lbl23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl23.setForeground(new java.awt.Color(102, 102, 102));
        lbl23.setText("que obtiene tu tienda.");
        jpnTercero.add(lbl23, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, -1, 30));

        lbl24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl24.setForeground(new java.awt.Color(102, 102, 102));
        lbl24.setText("Usa esta opción y maneja");
        jpnTercero.add(lbl24, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 160, -1));

        lbl25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl25.setForeground(new java.awt.Color(102, 102, 102));
        lbl25.setText("el sistema de Ventas.");
        jpnTercero.add(lbl25, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, -1, 30));

        lblMitad3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mitad3.jpg"))); // NOI18N
        lblMitad3.setText("jLabel2");
        jpnTercero.add(lblMitad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 600));

        jpnPrincipal.add(jpnTercero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 730, 600));

        jpnCuarto.setBackground(new java.awt.Color(0, 0, 0));
        jpnCuarto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl31.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl31.setForeground(new java.awt.Color(255, 255, 255));
        lbl31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_e738(1)_32.png"))); // NOI18N
        lbl31.setText("Productos");
        jpnCuarto.add(lbl31, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 130, -1));

        lbl32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl32.setForeground(new java.awt.Color(102, 102, 102));
        lbl32.setText("Podrás manejar los productos");
        jpnCuarto.add(lbl32, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 190, -1));

        lbl33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl33.setForeground(new java.awt.Color(102, 102, 102));
        lbl33.setText("de tu sistema de Tienda.");
        jpnCuarto.add(lbl33, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, 30));

        lbl34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl34.setForeground(new java.awt.Color(102, 102, 102));
        lbl34.setText("Usa esta opción para modificar,");
        jpnCuarto.add(lbl34, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 200, -1));

        lbl35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl35.setForeground(new java.awt.Color(102, 102, 102));
        lbl35.setText("agregar o eliminar Productos.");
        jpnCuarto.add(lbl35, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 190, 30));

        lblMitad4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mitad4.jpg"))); // NOI18N
        jpnCuarto.add(lblMitad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 360, 600));

        jpnPrincipal.add(jpnCuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 730, 600));

        jpnQuinto.setBackground(new java.awt.Color(0, 0, 0));
        jpnQuinto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl41.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl41.setForeground(new java.awt.Color(255, 255, 255));
        lbl41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Entypo_e78e(0)_32.png"))); // NOI18N
        lbl41.setText("Proveedores");
        jpnQuinto.add(lbl41, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 160, -1));

        lbl42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl42.setForeground(new java.awt.Color(102, 102, 102));
        lbl42.setText("¿Deseas nuevas alianzas?");
        jpnQuinto.add(lbl42, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, -1, -1));

        lbl43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl43.setForeground(new java.awt.Color(102, 102, 102));
        lbl43.setText("¡Estamos para eso!");
        jpnQuinto.add(lbl43, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, -1, 30));

        lbl44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl44.setForeground(new java.awt.Color(102, 102, 102));
        lbl44.setText("Usa esta opción y agrega");
        jpnQuinto.add(lbl44, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 160, -1));

        lbl45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl45.setForeground(new java.awt.Color(102, 102, 102));
        lbl45.setText("a tus nuevos proveedores.");
        jpnQuinto.add(lbl45, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, -1, 30));

        lblMitad5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mitad5.jpg"))); // NOI18N
        jpnQuinto.add(lblMitad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 600));

        jpnPrincipal.add(jpnQuinto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 730, 600));

        getContentPane().add(jpnPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 55, 750, 595));

        jpnRegistroCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardarprov.png"))); // NOI18N
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jpnRegistroCompra.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, 110, 30));

        txtIdCompra.setEditable(false);
        jpnRegistroCompra.add(txtIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 60, 30));

        jpnRegistroCompra.add(cmbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 180, 30));

        tblCompra =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id producto", "Producto", "Cantidad", "Costo", "SubTotal"
            }
        ));
        tblCompra.getTableHeader().setReorderingAllowed(false);
        tblCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCompraKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblCompraKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(tblCompra);

        jpnRegistroCompra.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 650, 200));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(102, 0, 0));
        jpnRegistroCompra.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 100, 40));

        txtFecha.setEditable(false);
        jpnRegistroCompra.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 160, 30));

        jPanel39.setBackground(new java.awt.Color(0, 0, 0));
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(240, 240, 240));
        jLabel33.setText("Agregar una Compra:");
        jPanel39.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, 30));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel39.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 20, 50));

        jpnRegistroCompra.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 50));

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFecha.setText("Fecha:");
        jpnRegistroCompra.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 60, 30));

        lblIdCompra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblIdCompra.setText("Id Compra:");
        jpnRegistroCompra.add(lblIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 80, 30));
        jpnRegistroCompra.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 95, 70, 30));
        jpnRegistroCompra.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 145, 40, 20));

        lblProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblProveedor.setText("Proveedor:");
        jpnRegistroCompra.add(lblProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 90, 30));
        jpnRegistroCompra.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 95, 70, 20));

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotal.setText("TOTAL:");
        jpnRegistroCompra.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, 50, 40));
        jpnRegistroCompra.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 530, 40, 40));

        lblCodBarraProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCodBarraProd.setText("Cod Barra:");
        jpnRegistroCompra.add(lblCodBarraProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 80, 30));

        txtCodBarraProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodBarraProdKeyTyped(evt);
            }
        });
        jpnRegistroCompra.add(txtCodBarraProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 120, 30));

        lblNomProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNomProd.setText("Producto:");
        jpnRegistroCompra.add(lblNomProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 70, 30));

        txtNomProd.setEditable(false);
        txtNomProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomProdKeyTyped(evt);
            }
        });
        jpnRegistroCompra.add(txtNomProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 140, 30));

        lblCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCantidad.setText("Cantidad:");
        jpnRegistroCompra.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 70, 30));

        txtCantidad.setText("1");
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        jpnRegistroCompra.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 40, 30));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jpnRegistroCompra.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 750, 10));

        jSeparator36.setBackground(new java.awt.Color(0, 0, 0));
        jpnRegistroCompra.add(jSeparator36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 750, 10));

        lblCostoProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCostoProd.setText("Costo:");
        jpnRegistroCompra.add(lblCostoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 194, 60, 20));

        txtCostoProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoProdKeyTyped(evt);
            }
        });
        jpnRegistroCompra.add(txtCostoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 80, 30));

        btnCancelarVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancelar.png"))); // NOI18N
        btnCancelarVenta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarVenta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarVenta1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarVenta1MouseExited(evt);
            }
        });
        btnCancelarVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVenta1ActionPerformed(evt);
            }
        });
        jpnRegistroCompra.add(btnCancelarVenta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 110, 30));

        getContentPane().add(jpnRegistroCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 730, 600));

        jpnVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jpnVentas.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 750, 10));

        tblProductosVender.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod Barra", "Producto", "Cantidad", "Precio Unitario $", "Sub Total $"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductosVender.getTableHeader().setReorderingAllowed(false);
        tblProductosVender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblProductosVenderKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductosVender);
        if (tblProductosVender.getColumnModel().getColumnCount() > 0) {
            tblProductosVender.getColumnModel().getColumn(0).setResizable(false);
            tblProductosVender.getColumnModel().getColumn(1).setResizable(false);
            tblProductosVender.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblProductosVender.getColumnModel().getColumn(2).setResizable(false);
        }

        jpnVentas.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 710, 190));

        btnVender.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/vender.png"))); // NOI18N
        btnVender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVenderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVenderMouseExited(evt);
            }
        });
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });
        jpnVentas.add(btnVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 540, 110, 30));

        btnBuscarProductoVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        btnBuscarProductoVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarProductoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarProductoVentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarProductoVentaMouseExited(evt);
            }
        });
        btnBuscarProductoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoVentaActionPerformed(evt);
            }
        });
        jpnVentas.add(btnBuscarProductoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 540, 110, 30));

        txtTotalventa.setEditable(false);
        txtTotalventa.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalventa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnVentas.add(txtTotalventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, 120, 40));

        txtNombreProductoVender.setEditable(false);
        txtNombreProductoVender.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnVentas.add(txtNombreProductoVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 350, 40));

        txtCantidadVender.setText("  1");
        txtCantidadVender.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCantidadVender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVenderKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVenderKeyTyped(evt);
            }
        });
        jpnVentas.add(txtCantidadVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 80, 40));

        btnAgregarProductoVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregarProductoVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar2.png"))); // NOI18N
        btnAgregarProductoVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProductoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarProductoVentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarProductoVentaMouseExited(evt);
            }
        });
        btnAgregarProductoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoVentaActionPerformed(evt);
            }
        });
        jpnVentas.add(btnAgregarProductoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 175, 110, 30));

        jPanel44.setBackground(new java.awt.Color(0, 0, 0));
        jPanel44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel44.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 20, 70));

        lblProveedores5.setBackground(new java.awt.Color(255, 255, 255));
        lblProveedores5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblProveedores5.setForeground(new java.awt.Color(255, 255, 255));
        lblProveedores5.setText("Ventas");
        jPanel44.add(lblProveedores5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 50));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("ID Venta");
        jPanel44.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 70, -1));

        txtClienteVenta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtClienteVenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel44.add(txtClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 280, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Cliente");
        jPanel44.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 70, -1));

        txtIdVenta.setEditable(false);
        txtIdVenta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtIdVenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel44.add(txtIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 140, 30));

        jpnVentas.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 70));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Producto");
        jpnVentas.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Cantidad");
        jpnVentas.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, -1, -1));
        jpnVentas.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 115, 60, -1));
        jpnVentas.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 115, 60, 10));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(204, 0, 0));
        jLabel35.setText("Total");
        jpnVentas.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 450, -1, -1));
        jpnVentas.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(617, 465, 40, 20));

        txtCodigoBarraVender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoBarraVenderKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarraVenderKeyTyped(evt);
            }
        });
        jpnVentas.add(txtCodigoBarraVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 140, 40));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Código de Barra");
        jpnVentas.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));
        jpnVentas.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 115, 100, 10));

        btnCancelarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancelar.png"))); // NOI18N
        btnCancelarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarVentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarVentaMouseExited(evt);
            }
        });
        btnCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVentaActionPerformed(evt);
            }
        });
        jpnVentas.add(btnCancelarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 110, 30));

        getContentPane().add(jpnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 730, 600));

        jpnProveedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminarProveedor.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminar.png"))); // NOI18N
        btnEliminarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarProveedorMouseExited(evt);
            }
        });
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });
        jpnProveedores.add(btnEliminarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 520, 110, 30));

        btnAgregarProveedor.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregarProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregarprov.png"))); // NOI18N
        btnAgregarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProveedor.setFocusCycleRoot(true);
        btnAgregarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarProveedorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarProveedorMouseExited(evt);
            }
        });
        jpnProveedores.add(btnAgregarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 520, 110, 30));

        btnModificarProveedor.setBackground(new java.awt.Color(0, 0, 0));
        btnModificarProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnModificarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar.png"))); // NOI18N
        btnModificarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarProveedorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModificarProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModificarProveedorMouseExited(evt);
            }
        });
        jpnProveedores.add(btnModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 520, 110, 30));

        tblProveedores =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idProveedor", "Nombre", "Teléfono", "Dirección", "NIT"
            }
        ));
        tblProveedores.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblProveedores);

        jpnProveedores.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 670, 260));

        jPanel42.setBackground(new java.awt.Color(0, 0, 0));
        jPanel42.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel42.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, 50));

        lblProveedores6.setBackground(new java.awt.Color(255, 255, 255));
        lblProveedores6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblProveedores6.setForeground(new java.awt.Color(255, 255, 255));
        lblProveedores6.setText("Proveedores");
        jPanel42.add(lblProveedores6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 30));

        jpnProveedores.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 50));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Listado de los Proveedores actuales:");
        jpnProveedores.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 175, -1, -1));
        jpnProveedores.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 230, -1));

        txtProveedoresBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProveedoresBuscarKeyTyped(evt);
            }
        });
        jpnProveedores.add(txtProveedoresBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 670, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Proveedor a buscar:");
        jpnProveedores.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 85, -1, -1));
        jpnProveedores.add(jSeparator38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 20));

        getContentPane().add(jpnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 730, 600));

        jpnAgregarProv.setPreferredSize(new java.awt.Dimension(95, 95));
        jpnAgregarProv.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardarprov.png"))); // NOI18N
        btnGuardarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarProveedorMouseExited(evt);
            }
        });
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });
        jpnAgregarProv.add(btnGuardarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 480, 110, 30));

        btnAtrasProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atras.png"))); // NOI18N
        btnAtrasProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtrasProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtrasProveedoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasProveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtrasProveedoresMouseExited(evt);
            }
        });
        jpnAgregarProv.add(btnAtrasProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, 110, 30));

        txtDireccionProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionProveedorActionPerformed(evt);
            }
        });
        txtDireccionProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionProveedorKeyTyped(evt);
            }
        });
        jpnAgregarProv.add(txtDireccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 410, 30));

        txtNombreProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNombreProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProveedorActionPerformed(evt);
            }
        });
        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyTyped(evt);
            }
        });
        jpnAgregarProv.add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 410, 30));

        jPanel45.setBackground(new java.awt.Color(0, 0, 0));
        jPanel45.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel45.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 20, 50));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Agrega un nuevo proveedor:");
        jPanel45.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID:");
        jPanel45.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, -1, 30));

        txtIDProveedor.setEditable(false);
        txtIDProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDProveedorKeyTyped(evt);
            }
        });
        jPanel45.add(txtIDProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 90, 30));

        jpnAgregarProv.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 50));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nombre:");
        jpnAgregarProv.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Teléfono:");
        jpnAgregarProv.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Dirección:");
        jpnAgregarProv.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, -1, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("NIT:");
        jpnAgregarProv.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, -1, 20));
        jpnAgregarProv.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 30, 10));
        jpnAgregarProv.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 50, 10));
        jpnAgregarProv.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 60, 10));
        jpnAgregarProv.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 60, 10));

        try {
            txtTelefonoProveedor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefonoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoProveedorActionPerformed(evt);
            }
        });
        jpnAgregarProv.add(txtTelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 230, 30));

        txtNIT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNITKeyTyped(evt);
            }
        });
        jpnAgregarProv.add(txtNIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 230, 30));

        getContentPane().add(jpnAgregarProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 730, 600));

        jpnModificarProveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardarModificarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardarprov.png"))); // NOI18N
        btnGuardarModificarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarModificarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarModificarProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarModificarProveedorMouseExited(evt);
            }
        });
        btnGuardarModificarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarModificarProveedorActionPerformed(evt);
            }
        });
        jpnModificarProveedor.add(btnGuardarModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, 110, 30));

        btnAtrasModificarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atras.png"))); // NOI18N
        btnAtrasModificarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtrasModificarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtrasModificarProveedorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasModificarProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtrasModificarProveedorMouseExited(evt);
            }
        });
        jpnModificarProveedor.add(btnAtrasModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 500, 110, 30));

        txtNuevoDireccionProveedor.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoDireccionProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoDireccionProveedorActionPerformed(evt);
            }
        });
        txtNuevoDireccionProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoDireccionProveedorKeyTyped(evt);
            }
        });
        jpnModificarProveedor.add(txtNuevoDireccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 410, 30));

        txtNuevoNombreProveedor.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoNombreProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNuevoNombreProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoNombreProveedorActionPerformed(evt);
            }
        });
        txtNuevoNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoNombreProveedorKeyTyped(evt);
            }
        });
        jpnModificarProveedor.add(txtNuevoNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 410, 30));

        jPanel48.setBackground(new java.awt.Color(0, 0, 0));
        jPanel48.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator40.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel48.add(jSeparator40, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 20, 50));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Modifica un proveedor:");
        jPanel48.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 170, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("ID:");
        jPanel48.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, -1, 30));

        txtIDProveedor1.setEditable(false);
        jPanel48.add(txtIDProveedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 90, 30));

        jpnModificarProveedor.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 50));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Nombre:");
        jpnModificarProveedor.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, 20));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Teléfono:");
        jpnModificarProveedor.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Dirección:");
        jpnModificarProveedor.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, -1, 20));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("NIT:");
        jpnModificarProveedor.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, -1, 20));
        jpnModificarProveedor.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 30, 10));
        jpnModificarProveedor.add(jSeparator41, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 50, 10));
        jpnModificarProveedor.add(jSeparator42, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 60, 10));
        jpnModificarProveedor.add(jSeparator43, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 60, 10));

        txtNuevoTelefono.setForeground(new java.awt.Color(102, 0, 0));
        try {
            txtNuevoTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNuevoTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoTelefonoActionPerformed(evt);
            }
        });
        jpnModificarProveedor.add(txtNuevoTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 230, 30));

        txtNuevoNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoNitKeyTyped(evt);
            }
        });
        jpnModificarProveedor.add(txtNuevoNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 200, 30));

        getContentPane().add(jpnModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 730, 600));

        jpnModificarProducto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardarModificarProveedor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardarprov.png"))); // NOI18N
        btnGuardarModificarProveedor1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarModificarProveedor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarModificarProveedor1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarModificarProveedor1MouseExited(evt);
            }
        });
        btnGuardarModificarProveedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarModificarProveedor1ActionPerformed(evt);
            }
        });
        jpnModificarProducto.add(btnGuardarModificarProveedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, 110, 30));

        btnAtrasModificarProveedor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atras.png"))); // NOI18N
        btnAtrasModificarProveedor1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtrasModificarProveedor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtrasModificarProveedor1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasModificarProveedor1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtrasModificarProveedor1MouseExited(evt);
            }
        });
        btnAtrasModificarProveedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasModificarProveedor1ActionPerformed(evt);
            }
        });
        jpnModificarProducto.add(btnAtrasModificarProveedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 500, 110, 30));

        txtNuevoInventarioProducto.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoInventarioProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNuevoInventarioProductoMouseClicked(evt);
            }
        });
        txtNuevoInventarioProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoInventarioProductoActionPerformed(evt);
            }
        });
        txtNuevoInventarioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoInventarioProductoKeyTyped(evt);
            }
        });
        jpnModificarProducto.add(txtNuevoInventarioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 80, 30));

        jPanel49.setBackground(new java.awt.Color(0, 0, 0));
        jPanel49.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator44.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel49.add(jSeparator44, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 20, 50));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Modifica un producto:");
        jPanel49.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 170, 30));

        txtNuevoCodBarraProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNuevoCodBarraProducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNuevoCodBarraProducto.setEnabled(false);
        txtNuevoCodBarraProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoCodBarraProductoKeyTyped(evt);
            }
        });
        jPanel49.add(txtNuevoCodBarraProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 100, 30));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Codigo de barra:");
        jPanel49.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, 30));

        jpnModificarProducto.add(jPanel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 50));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Nombre:");
        jpnModificarProducto.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, 20));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Inventario:");
        jpnModificarProducto.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, 20));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Costo:");
        jpnModificarProducto.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, -1, 20));
        jpnModificarProducto.add(jSeparator45, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 40, 10));
        jpnModificarProducto.add(jSeparator46, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, 10));
        jpnModificarProducto.add(jSeparator47, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 50, 10));
        jpnModificarProducto.add(jSeparator48, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 70, 10));

        txtNuevoNombreProducto.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoNombreProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNuevoNombreProductoMouseClicked(evt);
            }
        });
        txtNuevoNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoNombreProductoActionPerformed(evt);
            }
        });
        txtNuevoNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoNombreProductoKeyTyped(evt);
            }
        });
        jpnModificarProducto.add(txtNuevoNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 410, 30));

        txtNuevoCostoProducto.setForeground(new java.awt.Color(102, 0, 0));
        txtNuevoCostoProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNuevoCostoProductoMouseClicked(evt);
            }
        });
        txtNuevoCostoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoCostoProductoKeyTyped(evt);
            }
        });
        jpnModificarProducto.add(txtNuevoCostoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 90, 30));

        getContentPane().add(jpnModificarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 730, 600));

        jpnProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProductos =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código de Barra", "Nombre", "Inventario", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        tblProductos.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tblProductosInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblProductosKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductos);

        jpnProductos.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 650, 260));

        btnNuevoProducto.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevoProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo3.png"))); // NOI18N
        btnNuevoProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevoProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevoProductoMouseExited(evt);
            }
        });
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });
        jpnProductos.add(btnNuevoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 110, 30));

        btnBuscarProducto.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        btnBuscarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarProductoMouseExited(evt);
            }
        });
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });
        jpnProductos.add(btnBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 110, 30));

        btnModificarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar.png"))); // NOI18N
        btnModificarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModificarProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModificarProductoMouseExited(evt);
            }
        });
        btnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProductoActionPerformed(evt);
            }
        });
        jpnProductos.add(btnModificarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, 110, 30));

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminar.png"))); // NOI18N
        btnEliminarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarProductoMouseExited(evt);
            }
        });
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        jpnProductos.add(btnEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 510, 110, 30));

        jPanel43.setBackground(new java.awt.Color(0, 0, 0));
        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel43.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 20, 50));

        lblProveedores4.setBackground(new java.awt.Color(255, 255, 255));
        lblProveedores4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblProveedores4.setForeground(new java.awt.Color(255, 255, 255));
        lblProveedores4.setText("Productos");
        jPanel43.add(lblProveedores4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 30));

        jpnProductos.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 50));
        jpnProductos.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 186, 160, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Listado de los Productos:");
        jpnProductos.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        txtProductosBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductosBuscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductosBuscarKeyTyped(evt);
            }
        });
        jpnProductos.add(txtProductosBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 430, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Producto a buscar:");
        jpnProductos.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));
        jpnProductos.add(jSeparator37, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 96, 120, 20));

        getContentPane().add(jpnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 730, 600));

        jpnNuevoProducto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregarNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar2.png"))); // NOI18N
        btnAgregarNuevoProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarNuevoProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarNuevoProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarNuevoProductoMouseExited(evt);
            }
        });
        btnAgregarNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNuevoProductoActionPerformed(evt);
            }
        });
        jpnNuevoProducto.add(btnAgregarNuevoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 500, 110, 30));

        btnSalirProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atras.png"))); // NOI18N
        btnSalirProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalirProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalirProductosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalirProductosMouseExited(evt);
            }
        });
        btnSalirProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirProductosActionPerformed(evt);
            }
        });
        jpnNuevoProducto.add(btnSalirProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, 110, 30));

        txtCodBarraProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodBarraProductosActionPerformed(evt);
            }
        });
        txtCodBarraProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodBarraProductosKeyTyped(evt);
            }
        });
        jpnNuevoProducto.add(txtCodBarraProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 220, 30));

        txtNombreProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductosActionPerformed(evt);
            }
        });
        txtNombreProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProductosKeyTyped(evt);
            }
        });
        jpnNuevoProducto.add(txtNombreProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 270, 30));

        txtCostoProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoProductosKeyTyped(evt);
            }
        });
        jpnNuevoProducto.add(txtCostoProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 80, 30));

        jPanel46.setBackground(new java.awt.Color(0, 0, 0));
        jPanel46.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(240, 240, 240));
        jLabel34.setText("Agregar un nuevo producto:");
        jPanel46.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, 30));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel46.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 20, 50));

        jpnNuevoProducto.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 50));

        jLabel27.setBackground(new java.awt.Color(0, 0, 0));
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 0, 0));
        jLabel27.setText("Código de barra:");
        jpnNuevoProducto.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, 20));

        jLabel29.setBackground(new java.awt.Color(0, 0, 0));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Nombre:");
        jpnNuevoProducto.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, -1, 20));

        jLabel25.setBackground(new java.awt.Color(0, 0, 0));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Costo:");
        jpnNuevoProducto.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 60, 40));
        jpnNuevoProducto.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 100, 10));
        jpnNuevoProducto.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 70, 20));
        jpnNuevoProducto.add(jSeparator34, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 40, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Inventario:");
        jpnNuevoProducto.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, 20));

        txtInventarioProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInventarioProductoActionPerformed(evt);
            }
        });
        txtInventarioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInventarioProductoKeyTyped(evt);
            }
        });
        jpnNuevoProducto.add(txtInventarioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 60, 30));
        jpnNuevoProducto.add(jSeparator39, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 50, 20));

        getContentPane().add(jpnNuevoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 730, 600));

        jpnDetalleCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpnDetalleCompra.add(txtCodBarraProductos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 130, 30));
        jpnDetalleCompra.add(txtNombreProductos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 390, 30));

        jPanel47.setBackground(new java.awt.Color(0, 0, 0));
        jPanel47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(240, 240, 240));
        jLabel36.setText("Detalle de la Compra:");
        jPanel47.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 30));

        jSeparator28.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel47.add(jSeparator28, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 20, 50));

        jpnDetalleCompra.add(jPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 50));

        jLabel28.setBackground(new java.awt.Color(0, 0, 0));
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("ID Compra:");
        jpnDetalleCompra.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, 20));

        jLabel30.setBackground(new java.awt.Color(0, 0, 0));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Proveedor:");
        jpnDetalleCompra.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, 30));

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Costo:");
        jpnDetalleCompra.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 60, 20));
        jpnDetalleCompra.add(jSeparator29, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 40, 10));
        jpnDetalleCompra.add(jSeparator30, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 70, 10));
        jpnDetalleCompra.add(jSeparator31, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 185, 70, 10));

        tblDetalleCompra =new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblDetalleCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Producto", "Producto", "Cantidad", "Costo", "SubTotal"
            }
        ));
        tblDetalleCompra.setEnabled(false);
        tblDetalleCompra.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tblDetalleCompra);

        jpnDetalleCompra.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 670, 230));
        jpnDetalleCompra.add(jSeparator32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 232, 750, 10));

        jLabel37.setBackground(new java.awt.Color(0, 0, 0));
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Total:");
        jpnDetalleCompra.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, -1, -1));
        jpnDetalleCompra.add(jSeparator33, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 556, 40, -1));
        jpnDetalleCompra.add(txtPrecioProductos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 70, 30));

        txtTotal2.setText("$");
        jpnDetalleCompra.add(txtTotal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 530, 100, 40));

        btnAtrasDetalleCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atras.png"))); // NOI18N
        btnAtrasDetalleCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtrasDetalleCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtrasDetalleCompraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasDetalleCompraMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtrasDetalleCompraMouseExited(evt);
            }
        });
        jpnDetalleCompra.add(btnAtrasDetalleCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 535, 110, 30));

        getContentPane().add(jpnDetalleCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 730, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblBotonCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBotonCerrarMouseClicked
       System.exit(0);
    }//GEN-LAST:event_lblBotonCerrarMouseClicked

    /*  ---- Animaciones de los botones del menú ----  */
    private void btnComprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseEntered
    /*  ---- Animación compras, mover ----  */
        if(!compras)
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnCompras);
        Principal(false);
        Compras(true);
    }//GEN-LAST:event_btnComprasMouseEntered

    private void btnComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseExited
    /*  ---- Animación compras, volver posición anterior ----  */
        if(!compras)
        Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnCompras);
        Principal(true);
        Compras(false);
    }//GEN-LAST:event_btnComprasMouseExited

    private void btnVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseEntered
        if(!ventas)
            Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnVentas);
            Principal(false);
            Ventas(true);
    }//GEN-LAST:event_btnVentasMouseEntered

    private void btnVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseExited
        if(!ventas)
            Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnVentas);  
            Principal(true);
            Ventas(false);
    }//GEN-LAST:event_btnVentasMouseExited

    private void btnProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseEntered
        if(!productos)
            Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProductos);   
            Principal(false);
            Productos(true);
    }//GEN-LAST:event_btnProductosMouseEntered

    private void btnProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseExited
        if(!productos)
            Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnProductos); 
            Principal(true);
            Productos(false);
    }//GEN-LAST:event_btnProductosMouseExited

    private void btnProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseEntered
        if(!proveedores)
            Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProveedores);   
            Principal(false);
            Proveedores(true);
    }//GEN-LAST:event_btnProveedoresMouseEntered

    private void btnProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseExited
        if(!proveedores)
            Animacion.Animacion.mover_izquierda(0, -126, 1, 2, btnProveedores); 
            Principal(true);
            Proveedores(false);         
    }//GEN-LAST:event_btnProveedoresMouseExited

    private void btnProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedoresMouseClicked
        apagado();
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProveedores);  
        apagado2();
        jpnProveedores.setVisible(true); 
        tblProveedores.removeAll();
        modeloProveedores.setRowCount(0);
        actualizarTablaProveedor();
        txtProveedoresBuscar.requestFocus();
        if(txtProveedoresBuscar.getText().equals("")){
        } else {
            repaint();
            trsFiltro.setRowFilter(RowFilter.regexFilter("", 1));
            trsFiltro = new TableRowSorter(tblProveedores.getModel());
            tblProveedores.setRowSorter(trsFiltro);
            txtProveedoresBuscar.setText("");
        }
        
    }//GEN-LAST:event_btnProveedoresMouseClicked

    /*  ---- Acción de botones, cambiar de pantallas (Paneles) ----  */
    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        apagado2();
        apagado = false;
        jpnPrincipal.setVisible(true);
        Principal(true);
        Compras(false);
        Ventas(false);
        Productos(false);
        Proveedores(false); 
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnAgregarProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProveedorMouseClicked
        jpnProveedores.setVisible(false);
        jpnAgregarProv.setVisible(true);
        actualizarTablaProveedor();
        limpiandoTxtProveedor();
        
    }//GEN-LAST:event_btnAgregarProveedorMouseClicked

    private void btnAtrasProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasProveedoresMouseClicked
        jpnAgregarProv.setVisible(false);
        jpnProveedores.setVisible(true);
        limpiandoTxtProveedor();
        txtProveedoresBuscar.requestFocus();
    }//GEN-LAST:event_btnAtrasProveedoresMouseClicked

    private void btnVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseClicked
        apagado();
        apagado2();
        jpnVentas.setVisible(true);
        try {
            txtIdVenta.setText(String.valueOf(ControladorVenta.ObtenerIdVenta()));
        } catch (ErrorTienda ex) {
            Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtCodigoBarraVender.requestFocus();
    }//GEN-LAST:event_btnVentasMouseClicked

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        jpnProductos.setVisible(false);
        jpnNuevoProducto.setVisible(true);
        txtCodBarraProductos.requestFocus();
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void btnSalirProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirProductosActionPerformed
            jpnNuevoProducto.setVisible(false);
            jpnProductos.setVisible(true);
            DefaultTableModel modeloProductos=(DefaultTableModel) tblProductos.getModel();
            modeloProductos.setRowCount(0);
            limpiandoTxtProducto();
            txtProductosBuscar.requestFocus();
    }//GEN-LAST:event_btnSalirProductosActionPerformed

    private void btnProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseClicked
        apagado();
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnProductos);  
        apagado2();
        jpnProductos.setVisible(true);
        txtProductosBuscar.requestFocus();
    }//GEN-LAST:event_btnProductosMouseClicked

    private void btnAgregarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCompraActionPerformed
        jpnRegistroCompra.setVisible(true);
        jpnCompras.setVisible(false);
        txtCodBarraProd.requestFocus();
   
    }//GEN-LAST:event_btnAgregarCompraActionPerformed
    
    private void btnVerDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerDetalleMouseClicked
        jpnDetalleCompra.setVisible(true);
        jpnCompras.setVisible(false);
        
    }//GEN-LAST:event_btnVerDetalleMouseClicked

    private void btnComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasMouseClicked
        apagado();
        Animacion.Animacion.mover_derecha(-126, 0, 1, 2, btnCompras);  
        apagado2();
        jpnRegistroCompra.setVisible(true);
        LlenarCompra();
        txtCodBarraProd.requestFocus();
    }//GEN-LAST:event_btnComprasMouseClicked

    /*  ---- Mover barra ----  */
    private void jpnBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMousePressed
        x = evt.getX(); 
        y = evt.getY();
    }//GEN-LAST:event_jpnBarraSuperiorMousePressed

    private void jpnBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnBarraSuperiorMouseDragged
         this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jpnBarraSuperiorMouseDragged

    private void btnAtrasDetalleCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasDetalleCompraMouseClicked
        jpnDetalleCompra.setVisible(false);
        jpnCompras.setVisible(true);
    }//GEN-LAST:event_btnAtrasDetalleCompraMouseClicked

    /*  ---- Cambio de color a los botones ----  */
    private void btnAgregarProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProveedorMouseEntered
        // Cambio del botón Agregar Proveedor a negro:
        btnAgregarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/agregarprovB.png")));
    }//GEN-LAST:event_btnAgregarProveedorMouseEntered

    private void btnAgregarProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProveedorMouseExited
        // Cambio del botón Agregar Proveedor a blanco:
        btnAgregarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/agregarprov.png")));
    }//GEN-LAST:event_btnAgregarProveedorMouseExited

    private void btnModificarProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarProveedorMouseEntered
        // Cambio del botón Modificar Proveedor a negro:
        btnModificarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/modificarB.png")));
    }//GEN-LAST:event_btnModificarProveedorMouseEntered

    private void btnModificarProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarProveedorMouseExited
        // Cambio del botón Modificar Proveedor a blanco:
        btnModificarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
    }//GEN-LAST:event_btnModificarProveedorMouseExited

    private void btnEliminarProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarProveedorMouseEntered
        btnEliminarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/eliminarB.png")));
    }//GEN-LAST:event_btnEliminarProveedorMouseEntered

    private void btnEliminarProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarProveedorMouseExited
        btnEliminarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
    }//GEN-LAST:event_btnEliminarProveedorMouseExited

    private void btnAtrasProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasProveedoresMouseEntered
        btnAtrasProveedores.setIcon(new ImageIcon(getClass().getResource("/iconos/atrasB.png")));
    }//GEN-LAST:event_btnAtrasProveedoresMouseEntered

    private void btnAtrasProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasProveedoresMouseExited
        btnAtrasProveedores.setIcon(new ImageIcon(getClass().getResource("/iconos/atras.png")));
    }//GEN-LAST:event_btnAtrasProveedoresMouseExited

    private void btnGuardarProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarProveedorMouseEntered
        btnGuardarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/guardarprovB.png")));
    }//GEN-LAST:event_btnGuardarProveedorMouseEntered

    private void btnGuardarProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarProveedorMouseExited
        btnGuardarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/guardarprov.png")));
    }//GEN-LAST:event_btnGuardarProveedorMouseExited

    private void btnAgregarProductoVentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProductoVentaMouseEntered
        btnAgregarProductoVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/agregar2B.png")));
    }//GEN-LAST:event_btnAgregarProductoVentaMouseEntered

    private void btnAgregarProductoVentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProductoVentaMouseExited
        btnAgregarProductoVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/agregar2.png")));
    }//GEN-LAST:event_btnAgregarProductoVentaMouseExited

    private void btnBuscarProductoVentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarProductoVentaMouseEntered
        btnBuscarProductoVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/buscarB.png")));
    }//GEN-LAST:event_btnBuscarProductoVentaMouseEntered

    private void btnBuscarProductoVentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarProductoVentaMouseExited
        btnBuscarProductoVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/buscar.png")));
    }//GEN-LAST:event_btnBuscarProductoVentaMouseExited

    private void btnVenderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVenderMouseEntered
        btnVender.setIcon(new ImageIcon(getClass().getResource("/iconos/venderB.png")));
    }//GEN-LAST:event_btnVenderMouseEntered

    private void btnVenderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVenderMouseExited
        btnVender.setIcon(new ImageIcon(getClass().getResource("/iconos/vender.png")));
    }//GEN-LAST:event_btnVenderMouseExited

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        btnGuardar.setIcon(new ImageIcon(getClass().getResource("/iconos/guardarprovB.png")));
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        btnGuardar.setIcon(new ImageIcon(getClass().getResource("/iconos/guardarprov.png")));
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnNuevoProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoProductoMouseEntered
        btnNuevoProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/nuevo3B.png")));
    }//GEN-LAST:event_btnNuevoProductoMouseEntered

    private void btnNuevoProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoProductoMouseExited
        btnNuevoProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/nuevo3.png")));
    }//GEN-LAST:event_btnNuevoProductoMouseExited

    private void btnBuscarProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarProductoMouseEntered
        btnBuscarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/buscarB.png")));
    }//GEN-LAST:event_btnBuscarProductoMouseEntered

    private void btnBuscarProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarProductoMouseExited
        btnBuscarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/buscar.png")));
    }//GEN-LAST:event_btnBuscarProductoMouseExited

    private void btnModificarProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarProductoMouseEntered
        btnModificarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/modificarB.png")));
    }//GEN-LAST:event_btnModificarProductoMouseEntered

    private void btnModificarProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarProductoMouseExited
        btnModificarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
    }//GEN-LAST:event_btnModificarProductoMouseExited

    private void btnEliminarProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarProductoMouseEntered
        btnEliminarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/eliminarB.png")));
    }//GEN-LAST:event_btnEliminarProductoMouseEntered

    private void btnEliminarProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarProductoMouseExited
        btnEliminarProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
    }//GEN-LAST:event_btnEliminarProductoMouseExited

    private void btnSalirProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirProductosMouseEntered
        btnSalirProductos.setIcon(new ImageIcon(getClass().getResource("/iconos/atrasB.png")));
    }//GEN-LAST:event_btnSalirProductosMouseEntered

    private void btnSalirProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirProductosMouseExited
        btnSalirProductos.setIcon(new ImageIcon(getClass().getResource("/iconos/atras.png")));
    }//GEN-LAST:event_btnSalirProductosMouseExited

    private void btnAgregarNuevoProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarNuevoProductoMouseEntered
        btnAgregarNuevoProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/agregarB.png")));
    }//GEN-LAST:event_btnAgregarNuevoProductoMouseEntered

    private void btnAgregarNuevoProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarNuevoProductoMouseExited
        btnAgregarNuevoProducto.setIcon(new ImageIcon(getClass().getResource("/iconos/agregar.png")));
    }//GEN-LAST:event_btnAgregarNuevoProductoMouseExited

    private void btnAgregarCompraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarCompraMouseEntered
        btnAgregarCompra.setIcon(new ImageIcon(getClass().getResource("/iconos/agregar2B.png")));
    }//GEN-LAST:event_btnAgregarCompraMouseEntered

    private void btnAgregarCompraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarCompraMouseExited
        btnAgregarCompra.setIcon(new ImageIcon(getClass().getResource("/iconos/agregar2.png")));
    }//GEN-LAST:event_btnAgregarCompraMouseExited

    private void btnVerDetalleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerDetalleMouseEntered
        btnVerDetalle.setIcon(new ImageIcon(getClass().getResource("/iconos/detalles2B.png")));
    }//GEN-LAST:event_btnVerDetalleMouseEntered

    private void btnVerDetalleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerDetalleMouseExited
        btnVerDetalle.setIcon(new ImageIcon(getClass().getResource("/iconos/detalles2.png")));
    }//GEN-LAST:event_btnVerDetalleMouseExited

    private void btnAtrasDetalleCompraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasDetalleCompraMouseEntered
        btnAtrasDetalleCompra.setIcon(new ImageIcon(getClass().getResource("/iconos/atrasB.png")));
    }//GEN-LAST:event_btnAtrasDetalleCompraMouseEntered

    private void btnAtrasDetalleCompraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasDetalleCompraMouseExited
        btnAtrasDetalleCompra.setIcon(new ImageIcon(getClass().getResource("/iconos/atras.png")));
    }//GEN-LAST:event_btnAtrasDetalleCompraMouseExited

    private void btnGuardarModificarProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarModificarProveedorMouseEntered
        btnGuardarModificarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/guardarprovB.png")));
    }//GEN-LAST:event_btnGuardarModificarProveedorMouseEntered

    private void btnGuardarModificarProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarModificarProveedorMouseExited
        btnGuardarModificarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/guardarprov.png")));
    }//GEN-LAST:event_btnGuardarModificarProveedorMouseExited

    private void btnAtrasModificarProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasModificarProveedorMouseClicked
        jpnModificarProveedor.setVisible(false);
        jpnProveedores.setVisible(true);
        txtProveedoresBuscar.requestFocus();
    }//GEN-LAST:event_btnAtrasModificarProveedorMouseClicked

    private void btnAtrasModificarProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasModificarProveedorMouseEntered
        btnAtrasModificarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/atrasB.png")));
    }//GEN-LAST:event_btnAtrasModificarProveedorMouseEntered

    private void btnAtrasModificarProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasModificarProveedorMouseExited
        btnAtrasModificarProveedor.setIcon(new ImageIcon(getClass().getResource("/iconos/atras.png")));
    }//GEN-LAST:event_btnAtrasModificarProveedorMouseExited

    private void btnModificarProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarProveedorMouseClicked
        if(tblProveedores.getSelectedRow()!=-1){
            jpnProveedores.setVisible(false);
            jpnModificarProveedor.setVisible(true);
            txtIDProveedor1.setText(tblProveedores.getValueAt(tblProveedores.getSelectedRow(), 0).toString());
            txtNuevoNombreProveedor.setText(tblProveedores.getValueAt(tblProveedores.getSelectedRow(), 1).toString());
            txtNuevoTelefono.setText(tblProveedores.getValueAt(tblProveedores.getSelectedRow(), 2).toString());
            txtNuevoDireccionProveedor.setText(tblProveedores.getValueAt(tblProveedores.getSelectedRow(), 3).toString());
            txtNuevoNit.setText(tblProveedores.getValueAt(tblProveedores.getSelectedRow(), 4).toString());
            txtNuevoNombreProveedor.requestFocus();
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Primero debe de seleccionar un proveedor");
        }
        
    }//GEN-LAST:event_btnModificarProveedorMouseClicked

    private void btnAgregarProductoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoVentaActionPerformed
      if(txtCantidadVender.getText().isEmpty()){
          JOptionPane.showMessageDialog(rootPane, "Ingrese una cantidad, campo vacío");
          txtCantidadVender.requestFocus();
          txtCantidadVender.selectAll();
      }else{
          tablaProductosVender();
      }
            
      
        
    }//GEN-LAST:event_btnAgregarProductoVentaActionPerformed

    private void txtInventarioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInventarioProductoKeyTyped
        int c=(int) evt.getKeyChar();
        
        if ((c >=48 && c<=57) || (c==8) || (c== (char)KeyEvent.VK_BACK_SPACE) || (c== (char)KeyEvent.VK_ENTER)) {
            //No pasa nada
        }else{
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
        }
        
    }//GEN-LAST:event_txtInventarioProductoKeyTyped

    private void txtNombreProductosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductosKeyTyped
         int c=(int) evt.getKeyChar();
         char mayu=evt.getKeyChar();
         
         if ((c>=65 && c<=90) || (c>=97 && c<=122)  || (c==32) || (c==8) || (c >=48 && c<=57) || (c== (char)KeyEvent.VK_BACK_SPACE) || (c== (char)KeyEvent.VK_ENTER)) {
             if (Character.isLowerCase(mayu)) {
                 String cadena=(""+mayu).toUpperCase();
                 mayu=cadena.charAt(0);
                 evt.setKeyChar(mayu);
             }
        }else{
             evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
         }
        
    }//GEN-LAST:event_txtNombreProductosKeyTyped

    private void txtCostoProductosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoProductosKeyTyped
        int c=(int) evt.getKeyChar();
        
        if ((c >=48 && c<=57)  || (c==46) || (c==8) || (c== (char)KeyEvent.VK_BACK_SPACE) || (c== (char)KeyEvent.VK_ENTER)) {
            if (c==46) {
                String cadena=txtCostoProductos.getText();
            int tamanio=cadena.length();
            for (int i = 0; i <= tamanio; i++) {
                if (cadena.contains(".")) {
                    evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                    getToolkit().beep();
                    evt.consume();
                }
            }
            }
        }else{
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCostoProductosKeyTyped

    private void txtCodBarraProductosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodBarraProductosKeyTyped
         int limiteCaracter=13;
       
       int c=(int) evt.getKeyChar();
        int limiteCaracteres=13;
        
         
         if ((c >=48 && c<=57) || (c==8) || (c== (char)KeyEvent.VK_BACK_SPACE) || (c== (char)KeyEvent.VK_ENTER)) {
             if(txtCodBarraProductos.getText().length()==limiteCaracter){
                getToolkit().beep();
                evt.consume();
             }
         }else{
             getToolkit().beep();
             evt.consume();
         }
    }//GEN-LAST:event_txtCodBarraProductosKeyTyped

    private void btnAgregarNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNuevoProductoActionPerformed
       Producto agregado=new Producto();
       
      
        if (txtCodBarraProductos.equals("") || txtNombreProductos.equals("") || txtInventarioProducto.getText().equals("") || txtCostoProductos.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        
        }else{
            
            int inven=Integer.parseInt(txtInventarioProducto.getText());
            double cost=Double.parseDouble(txtCostoProductos.getText());
            if(inven<=0 || cost<=0){
                JOptionPane.showMessageDialog(null, "Los campos inventario y costo no pueden estar en cero o menor que cero");
            }else{
                    agregado.setCodBarra(txtCodBarraProductos.getText());
                    agregado.setNombre(txtNombreProductos.getText());
                    agregado.setInventario(Integer.parseInt(txtInventarioProducto.getText()));
                    agregado.setCosto(Double.parseDouble(txtCostoProductos.getText()));


                try {
                    ControladorProducto.Agregar(agregado);

                    JOptionPane.showMessageDialog(null, "El producto fue agregado correctamente");
                    limpiandoTxtProducto();
                } catch (ErrorTienda e) {

                }
            }
            
        }
       
    }//GEN-LAST:event_btnAgregarNuevoProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        int fila=tblProductos.getSelectedRow(); 
        if (tblProductos.isRowSelected(fila)) {
            
            if (fila>=0) {
                
            
            Producto eliminar=new Producto();
            ControladorProducto controlador=new ControladorProducto();
            DefaultTableModel modeloProductos=(DefaultTableModel) tblProductos.getModel();
            

            String codBarra=tblProductos.getValueAt(fila, 0).toString();
            String nombre=tblProductos.getValueAt(fila, 1).toString();
            int inventario=Integer.parseInt(tblProductos.getValueAt(fila, 2).toString());
            double costo=Double.parseDouble(tblProductos.getValueAt(fila, 3).toString());

            eliminar.setCodBarra(codBarra);
            eliminar.setNombre(nombre);
            eliminar.setInventario(inventario);
            eliminar.setCosto(costo);


            try {
                ControladorProducto.Eliminar(eliminar);
                if (ControladorProducto.isCambio()) {
                    JOptionPane.showMessageDialog(null, "No puede eliminar este producto porque tiene registros vigentes");
                }else{
                    modeloProductos.removeRow(fila);
                    txtProductosBuscar.setText("");
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado con exito");
                }
                
            } catch (ErrorTienda ex) {
                
            }
            }
        }else{
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fila o la tabla esta vacia");
        }
        
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void txtProductosBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductosBuscarKeyTyped
        char mayu=evt.getKeyChar();      
         int c=(int) evt.getKeyChar();
        int limiteCaracteres=13;
        
         
         if ((c >=48 && c<=57) || (c>=65 && c<=90) || (c>=97 && c<=122) || (c==32) || (c==8) || (c== (char)KeyEvent.VK_ENTER)) {
             if (txtProductosBuscar.getText().length()==limiteCaracteres) {
                 getToolkit().beep();
                 evt.consume();
             }else if (Character.isLowerCase(mayu)) {
                     String cadena=(""+mayu).toUpperCase();
                     mayu=cadena.charAt(0);
                     evt.setKeyChar(mayu);
             }
        }else{
             evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
         }
    }//GEN-LAST:event_txtProductosBuscarKeyTyped

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
            tablaBuscarProductos();
            
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        int fila=tblProductos.getSelectedRow();
        
        if (tblProductos.isRowSelected(fila)) {
            disableBotonesProducto(true);
        }
        
    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProductoActionPerformed
        
        if(tblProductos.getSelectedRow()!=-1){
            int seleccion; 
            seleccion = tblProductos.getSelectedRow();
            txtNuevoCodBarraProducto.setText(tblProductos.getValueAt(seleccion, 0).toString());
            txtNuevoNombreProducto.setText((tblProductos.getValueAt(seleccion, 1).toString()));
            txtNuevoInventarioProducto.setText((tblProductos.getValueAt(seleccion, 2).toString()));
            txtNuevoCostoProducto.setText((tblProductos.getValueAt(seleccion, 3).toString()));
            jpnModificarProducto.setVisible(true);
            jpnProductos.setVisible(false);
            txtNuevoNombreProducto.requestFocus();
            txtNuevoNombreProducto.selectAll();
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione un dato de la tabla");
        }
    }//GEN-LAST:event_btnModificarProductoActionPerformed

    private void tblProductosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyTyped
//        char mayu=evt.getKeyChar();      
//         int c=(int) evt.getKeyChar();


         int fila=tblProductos.getSelectedRow();
         
         if (tblProductos.isRowSelected(fila)) {
              if(KeyEvent.VK_DELETE==127){
                if (fila>=0) {
                
            
            Producto eliminar=new Producto();
            DefaultTableModel modeloProductos=(DefaultTableModel) tblProductos.getModel();
            

            String codBarra=tblProductos.getValueAt(fila, 0).toString();
            String nombre=tblProductos.getValueAt(fila, 1).toString();
            int inventario=Integer.parseInt(tblProductos.getValueAt(fila, 2).toString());
            double costo=Double.parseDouble(tblProductos.getValueAt(fila, 3).toString());

            eliminar.setCodBarra(codBarra);
            eliminar.setNombre(nombre);
            eliminar.setInventario(inventario);
            eliminar.setCosto(costo);


            try {
                ControladorProducto.Eliminar(eliminar);
                modeloProductos.removeRow(fila);
                JOptionPane.showMessageDialog(null, "El registro fue eliminado con exito");
            } catch (ErrorTienda ex) {
                
            }
            }
        }else{
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fila o la tabla esta vacia");
        }
              
        }
        
    
         
        
    }//GEN-LAST:event_tblProductosKeyTyped


    private void tblProductosInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblProductosInputMethodTextChanged
    }//GEN-LAST:event_tblProductosInputMethodTextChanged

    private void txtCodigoBarraVenderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarraVenderKeyPressed
        char c=evt.getKeyChar();      
         
         
         if (c == (char) KeyEvent.VK_ENTER) {
             buscarProductoVender();
             
           
        }
    }//GEN-LAST:event_txtCodigoBarraVenderKeyPressed

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        Proveedor agregado=new Proveedor();
        int idProv;
        encontradoProv = false;
        if (txtDireccionProveedor.getText().equals("") || txtNombreProveedor.getText().equals("") || txtNIT.getText().equals("") || txtTelefonoProveedor.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }
        else{
            if(txtNIT.getText().length() != 14){
                JOptionPane.showMessageDialog(null, "El NIT debe tener 14 digitos");
                txtNIT.requestFocus();
                txtNIT.selectAll();
            }
            else{
            try {
                idProv = ControladorProveedor.ObtenerIdProveedor();
                agregado.setIdProveedor(idProv+1);
            } catch (ErrorTienda ex) {
                Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
              }
              if (tblProveedores.getRowCount()>0) {
                  int i = 0;
                  while (encontradoProv==false&&i<tblProveedores.getRowCount()) {
                     encontradoProv = tblProveedores.getValueAt(i, 1).equals(txtNombreProveedor.getText());
                     i++;
                  }
              }
              if(encontradoProv == false){
                  agregado.setNombre(txtNombreProveedor.getText());
                  agregado.setTelefono(txtTelefonoProveedor.getText());
                  agregado.setNIT(txtNIT.getText());
                  agregado.setDireccion(txtDireccionProveedor.getText());
              try {
              ControladorProveedor.Agregar(agregado);
              JOptionPane.showMessageDialog(rootPane, "El Proveedor fue agregado correctamente.");
              limpiandoTxtProveedor();
              tblProveedores.removeAll();
              actualizarTablaProveedor();
              
              } catch (ErrorTienda e) {      
           }
        }else{JOptionPane.showMessageDialog(null, "El nombre de ese proveedor ya está registrado, cámbielo.");}
              encontradoProv=false;
              txtNombreProveedor.requestFocus();
              txtNombreProveedor.selectAll();
                
        }
        }
        LlenarCompra();
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void txtNombreProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyTyped
         char mayu=evt.getKeyChar();        
         if (Character.isLowerCase(mayu)) {
                 String cadena=(""+mayu).toUpperCase();
                 mayu=cadena.charAt(0);
                 evt.setKeyChar(mayu);
        }
        else{

         }
    }//GEN-LAST:event_txtNombreProveedorKeyTyped

    private void txtDireccionProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionProveedorKeyTyped
         char mayu=evt.getKeyChar();        
         if (Character.isLowerCase(mayu)) {
                 String cadena=(""+mayu).toUpperCase();
                 mayu=cadena.charAt(0);
                 evt.setKeyChar(mayu);
        }
        else{

         }
    }//GEN-LAST:event_txtDireccionProveedorKeyTyped

    private void txtIDProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDProveedorKeyTyped
        
    }//GEN-LAST:event_txtIDProveedorKeyTyped

    private void txtNuevoNombreProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoNombreProveedorKeyTyped
         char mayu=evt.getKeyChar();        
         if (Character.isLowerCase(mayu)) {
                 String cadena=(""+mayu).toUpperCase();
                 mayu=cadena.charAt(0);
                 evt.setKeyChar(mayu);
        }
        else{

         }
    }//GEN-LAST:event_txtNuevoNombreProveedorKeyTyped

    private void txtNuevoDireccionProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoDireccionProveedorKeyTyped
         char mayu=evt.getKeyChar();        
         if (Character.isLowerCase(mayu)) {
                 String cadena=(""+mayu).toUpperCase();
                 mayu=cadena.charAt(0);
                 evt.setKeyChar(mayu);
        }
        else{

         }
    }//GEN-LAST:event_txtNuevoDireccionProveedorKeyTyped

    private void btnGuardarModificarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarModificarProveedorActionPerformed
        encontradoProv = false;
        if (!txtNuevoDireccionProveedor.getText().equals("") && !txtNuevoNombreProveedor.getText().equals("") && !txtNuevoNit.getText().equals("") && !txtNuevoTelefono.getText().equals("")) {
            if(txtNuevoNit.getText().length() != 14){
                JOptionPane.showMessageDialog(null, "El NIT debe tener 14 digitos");
                txtNuevoNit.requestFocus();
                txtNuevoNit.selectAll();
            }
            else{
            if (tblProveedores.getRowCount()>0) {
                  int i = 0;
                  while (encontradoProv==false&&i<tblProveedores.getRowCount()) {
                     encontradoProv = tblProveedores.getValueAt(i, 1).equals(txtNuevoNombreProveedor.getText());
                     i++;
                  }
            }
            if(txtNuevoNombreProveedor.getText().equals(tblProveedores.getValueAt(tblProveedores.getSelectedRow(), 1).toString())){
                encontradoProv = false;
            }
            if(encontradoProv == false){
            Proveedor proveedor = new Proveedor();
            proveedor.setIdProveedor(Integer.parseInt(txtIDProveedor1.getText()));
            proveedor.setNombre(txtNuevoNombreProveedor.getText());
            proveedor.setTelefono(txtNuevoTelefono.getText());
            proveedor.setDireccion(txtNuevoDireccionProveedor.getText());
            proveedor.setNIT(txtNuevoNit.getText());
            try{
                ControladorProveedor.Modificar(proveedor);
                JOptionPane.showMessageDialog(rootPane, "Datos modificados");
            } catch(ErrorTienda ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
            tblProveedores.removeAll();
            actualizarTablaProveedor();
            jpnProveedores.setVisible(true);
            jpnModificarProveedor.setVisible(false);
            txtProveedoresBuscar.requestFocus();
        }else{JOptionPane.showMessageDialog(null, "El nombre de ese proveedor ya está registrado, cámbielo.");}}
                
        }else{
            JOptionPane.showMessageDialog(rootPane, "Debe de rellenar todos los campos");
        }   
        encontradoProv=false;
        txtNuevoNombreProveedor.requestFocus();
        txtNuevoNombreProveedor.selectAll();
    }//GEN-LAST:event_btnGuardarModificarProveedorActionPerformed

    private void txtProveedoresBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedoresBuscarKeyTyped
         char mayu=evt.getKeyChar();        
         if (Character.isLowerCase(mayu)) {
                 String cadena=(""+mayu).toUpperCase();
                 mayu=cadena.charAt(0);
                 evt.setKeyChar(mayu);
        }
        else{
         }
        txtProveedoresBuscar.addKeyListener(new KeyAdapter(){
            
            @Override
            public void keyReleased(final KeyEvent e){
                String cadena = (txtProveedoresBuscar.getText());
                txtProveedoresBuscar.setText(cadena);
                repaint();
                trsFiltro.setRowFilter(RowFilter.regexFilter(txtProveedoresBuscar.getText(), 1));
            }
        });
        trsFiltro = new TableRowSorter(tblProveedores.getModel());
        tblProveedores.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtProveedoresBuscarKeyTyped

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
//        if(tblProveedores.getSelectedRow()!=-1){

            
            
         int fila = tblProveedores.getSelectedRow(); 
         System.out.println(fila);
        if (tblProveedores.isRowSelected(fila)) {
            
            if (fila>=0) {
            int seleccion;
            seleccion = tblProveedores.getSelectedRow();
            DefaultTableModel modeloProveedores=(DefaultTableModel) tblProveedores.getModel();
            Proveedor proveedor = new Proveedor();
            
            int idProve=Integer.parseInt(tblProveedores.getValueAt(seleccion, 0).toString());
            String nom=tblProveedores.getValueAt(seleccion, 1).toString();
            String tel=tblProveedores.getValueAt(seleccion, 2).toString();
            String dire=tblProveedores.getValueAt(seleccion, 3).toString();
            String nit=tblProveedores.getValueAt(seleccion, 4).toString();
            
            
            
            proveedor.setIdProveedor(Integer.parseInt(tblProveedores.getValueAt(seleccion, 0).toString()));
            proveedor.setNombre(tblProveedores.getValueAt(seleccion, 1).toString());
            proveedor.setTelefono(tblProveedores.getValueAt(seleccion, 2).toString());
            proveedor.setDireccion(tblProveedores.getValueAt(seleccion, 3).toString());
            proveedor.setNIT(tblProveedores.getValueAt(seleccion, 4).toString());
            try{
                ControladorProveedor.Eliminar(proveedor);
                if (ControladorProveedor.isCambio()) {
                    JOptionPane.showMessageDialog(null, "No puede eliminar este producto porque tiene registros vigentes");
                }else{
                    modeloProveedores.removeRow(fila);
                    txtProveedoresBuscar.setText("");
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado con exito");
                }
                
                
            }catch(ErrorTienda ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
           
            
        }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione un proveedor de la tabla");
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void txtCantidadVenderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVenderKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            
                if (c != (char) KeyEvent.VK_BEGIN) {
                    if (c != (char) KeyEvent.VK_BACK_SPACE) {
                        if (c != (char) KeyEvent.VK_DELETE) {
                            if (c != (char) KeyEvent.VK_ENTER) {
                            evt.consume();
                                mensajeNotificacion("Ingrese solo numeros enteros");
                        }
                    }
                }
                }
        }
    }//GEN-LAST:event_txtCantidadVenderKeyTyped

    private void txtCodigoBarraVenderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarraVenderKeyTyped
       
       char c = evt.getKeyChar();
    if(txtCodigoBarraVender.getText().length()>=13){
               evt.consume();
        }else{
        if (c < '0' || c > '9') {
            
                if (c != (char) KeyEvent.VK_BEGIN) {
                    if (c != (char) KeyEvent.VK_BACK_SPACE) {
                        if (c != (char) KeyEvent.VK_DELETE) {
                            if (c != (char) KeyEvent.VK_ENTER) {
                            evt.consume();
                                mensajeNotificacion("Solo se permiten números");
                        }
                    }
                }
                }
        }   
    }
       
    }//GEN-LAST:event_txtCodigoBarraVenderKeyTyped

    private void btnGuardarModificarProveedor1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarModificarProveedor1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarModificarProveedor1MouseEntered

    private void btnGuardarModificarProveedor1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarModificarProveedor1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarModificarProveedor1MouseExited

    private void btnGuardarModificarProveedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarModificarProveedor1ActionPerformed
        if (txtNuevoCodBarraProducto.getText().equals("")||txtNuevoNombreProducto.getText().equals("")||txtNuevoInventarioProducto.getText().equals("")||
                txtNuevoCostoProducto.getText().equals("")) {
            
            JOptionPane.showMessageDialog(rootPane, "Debe de rellenar todos los campos");
           
        }else{
            DefaultTableModel modeloProductos=(DefaultTableModel) tblProductos.getModel();
            Producto producto = new Producto();
            producto.setCodBarra(txtNuevoCodBarraProducto.getText());
            producto.setNombre(txtNuevoNombreProducto.getText());
            producto.setInventario(Integer.parseInt(txtNuevoInventarioProducto.getText()));
            producto.setCosto(Double.parseDouble(txtNuevoCostoProducto.getText()));
            try {
                ControladorProducto.Modificar(producto);
                modeloProductos.setRowCount(0);
                txtProductosBuscar.setText("");
                JOptionPane.showMessageDialog(rootPane, "Modificado con exito");
            } catch (ErrorTienda ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
            //tblProveedores.removeAll();
            jpnProductos.setVisible(true);
            jpnModificarProducto.setVisible(false);
            
        }
    }//GEN-LAST:event_btnGuardarModificarProveedor1ActionPerformed

    private void btnAtrasModificarProveedor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasModificarProveedor1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasModificarProveedor1MouseClicked

    private void btnAtrasModificarProveedor1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasModificarProveedor1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasModificarProveedor1MouseEntered

    private void btnAtrasModificarProveedor1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasModificarProveedor1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasModificarProveedor1MouseExited

    private void txtCodBarraProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodBarraProdKeyTyped
       
        char ch = evt.getKeyChar();
        if(txtCodBarraProd.getText().length()>=13){
           evt.consume();
        }else{
            if (ch < '0' || ch > '9') {

                if (ch != (char) KeyEvent.VK_BEGIN) {
                    if (ch != (char) KeyEvent.VK_BACK_SPACE) {
                        if (ch != (char) KeyEvent.VK_DELETE) {
                            if(ch != (char) KeyEvent.VK_ENTER){

                                evt.consume();
                                JOptionPane.showMessageDialog(null, "Solo Numeros", "Error", JOptionPane.ERROR_MESSAGE);

                            }
                        }
                    }
                }
            }   
        }
        char c = evt.getKeyChar();               
             if (c == (char) KeyEvent.VK_ENTER) {
                String codBarra=txtCodBarraProd.getText();
                String producto;

                    try {
                        if (codBarra.equals("")) {
                            JOptionPane.showMessageDialog(rootPane, "Ingrese un codigo de barras");
                        } else {
                            ControladorProducto.Obtener(codBarra);
                            producto= ControladorProducto.Obtener(codBarra).getNombre();
                            //PARA SABER SI EXISTE O NO EXISTE UN PRODUCTO
                            if (producto==null || producto=="") {
                                txtNomProd.setEditable(true);
                                txtNomProd.requestFocus();                          
                                JOptionPane.showMessageDialog(rootPane, "El producto no esta guardado, agregar");
                                exprod=false;
                            } else {
                                txtNomProd.setText(producto);
                                txtCantidad.requestFocus();
                                txtCantidad.selectAll();
                                exprod=true;
                            }
                        }
                        


                    } catch (ErrorTienda ex) {
                        Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
        }
    
             
           
        
    }//GEN-LAST:event_txtCodBarraProdKeyTyped
public void AgregarProductoCompras(){
    Producto pr = new Producto();
    pr.setCodBarra(txtCodBarraProd.getText());
                pr.setNombre(txtNomProd.getText());
                pr.setInventario(Integer.parseInt(txtCantidad.getText()));
                pr.setCosto(Double.parseDouble(txtCostoProd.getText()));
                    try {
                        ControladorProducto.Agregar(pr);
                    } catch (ErrorTienda ex) {
                        Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 exprod=true;
                 txtNomProd.setEditable(false);
                 AgregarProductoTablaCompras();
                 JOptionPane.showMessageDialog(rootPane, "Producto Agregado");
}
public void AgregarProductoTablaCompras(){
    if (tblCompra.getRowCount()>0) {
                    int i = 0;
                    while (encontrado==false&&i<tblCompra.getRowCount()) {
                        encontrado = tblCompra.getValueAt(i, 0).equals(txtCodBarraProd.getText());
                        i++;
                    }
                }
                
                
                if(encontrado == false){
                    String fila[]  = new String[5];
                    fila[0]=txtCodBarraProd.getText();
                    fila[1]=txtNomProd.getText();
                    fila[2]=txtCantidad.getText();
                    //System.out.println(decimalProductos.format(txtCostoProd.getText().toString()));
                    fila[3]=decimalProductos.format(Double.parseDouble(txtCostoProd.getText()));
                    fila[4]=String.valueOf(decimal.format((Double.parseDouble(txtCantidad.getText()))*(Double.parseDouble(txtCostoProd.getText()))));
                    tablaModel.addRow(fila);
                    tblCompra.setModel(tablaModel);
                }else{
                    boolean buscar=false;
                    int j=0;
                    int nuevaCantidad;
                    double nuevoCosto;
                    while (buscar==false) {
                        buscar = tblCompra.getValueAt(j, 0).equals(txtCodBarraProd.getText());
                        j++;
                    }
                    nuevaCantidad = Integer.parseInt(txtCantidad.getText()) + Integer.parseInt(tblCompra.getValueAt(j-1, 2).toString());
                    nuevoCosto = (Double.parseDouble(txtCostoProd.getText()) + Double.parseDouble(tblCompra.getValueAt(j-1, 3).toString()))/2;
                    System.out.println(j);
                    tablaModel.setValueAt(nuevaCantidad, j-1, 2);
                    tablaModel.setValueAt(decimalProductos.format(nuevoCosto), j-1, 3);
                    tablaModel.setValueAt(decimal.format(nuevaCantidad*nuevoCosto), j-1, 4);
                    tblCompra.setModel(tablaModel);
                }
                encontrado = false;
                //LIMPIAR LOS TXT 
                txtCodBarraProd.setText("");
                txtNomProd.setText("");
                txtCantidad.setText("1");
                txtCostoProd.setText("");
                txtCodBarraProd.requestFocus();

                int filas = tablaModel.getRowCount();
                int iteraciones=0;
                double total=0;
                while(iteraciones<filas){
                   total+=Double.parseDouble(String.valueOf(tablaModel.getValueAt(iteraciones, 4)));
                   iteraciones++;}

                double totalFinal=Double.parseDouble(decimal.format(total));
                txtTotal.setText("$"+totalFinal);
}
    private void txtCostoProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoProdKeyTyped
        // TODO add your handling code here:
        
        s = evt.getKeyChar();
        int p=(int) evt.getKeyChar();
        encontrado = false;
        
      // double actualizarPrecio = ((((CantidadActual)*(PrecioActual))+((detalleCompra.get(i).getCantidad())*(detalleCompra.get(i).getCostoUnitario())))/((detalleCompra.get(i).getCantidad())+CantidadActual)); 
        if (!Character.isDigit(s) && s != KeyEvent.VK_PERIOD) {
            getToolkit().beep();
            evt.consume();
        }else{
            if (p==46) {
                String cadena=txtCostoProd.getText();
            int tamanio=cadena.length();
            for (int i = 0; i <= tamanio; i++) {
                if (cadena.contains(".")) {
                    evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                    getToolkit().beep();
                    evt.consume();
                }
            }
            }
        }
        //AGREGAR COMPRAS A LA TABLA
        char c = evt.getKeyChar();
        if (c == (char) KeyEvent.VK_ENTER) {
            if (txtCodBarraProd.getText().equals("")||txtNomProd.getText().equals("")||txtCostoProd.getText().equals("")||txtCantidad.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Llene todos los campos");
            }else{
            if(Double.parseDouble(txtCostoProd.getText()) > 0){
            
            
            if (exprod==false){
                AgregarProductoCompras();
            }else{
                AgregarProductoTablaCompras();
            
        }
                           }else{
                       JOptionPane.showMessageDialog(rootPane, "El Costo debe ser mayor a 0");
                       }}
        }
    }//GEN-LAST:event_txtCostoProdKeyTyped

    private void txtNuevoCodBarraProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoCodBarraProductoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            
                if (c != (char) KeyEvent.VK_BEGIN) {
                    if (c != (char) KeyEvent.VK_BACK_SPACE) {
                        if (c != (char) KeyEvent.VK_DELETE) {
                            if (c != (char) KeyEvent.VK_ENTER) {
                            evt.consume();
                            JOptionPane.showMessageDialog(null, "Solo Numeros", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                }
        }
    }//GEN-LAST:event_txtNuevoCodBarraProductoKeyTyped

    private void txtNuevoInventarioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoInventarioProductoKeyTyped
         char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            
                if (c != (char) KeyEvent.VK_BEGIN) {
                    if (c != (char) KeyEvent.VK_BACK_SPACE) {
                        if (c != (char) KeyEvent.VK_DELETE) {
                            if (c != (char) KeyEvent.VK_ENTER) {
                            evt.consume();
                            JOptionPane.showMessageDialog(null, "Solo Numeros", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                }
        }
    }//GEN-LAST:event_txtNuevoInventarioProductoKeyTyped

    private void txtNuevoNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoNombreProductoKeyTyped
        char mayu=evt.getKeyChar();        
         if (Character.isLowerCase(mayu)) {
                 String cadena=(""+mayu).toUpperCase();
                 mayu=cadena.charAt(0);
                 evt.setKeyChar(mayu);
        }
        else{

         }
    }//GEN-LAST:event_txtNuevoNombreProductoKeyTyped

    private void txtNuevoCostoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoCostoProductoKeyTyped
        int c=(int) evt.getKeyChar();
        
        if ((c >=48 && c<=57)  || (c==46) || (c==8)) {
            if (c==46) {
                String cadena=txtNuevoCostoProducto.getText();
            int tamanio=cadena.length();
            for (int i = 0; i <= tamanio; i++) {
                if (cadena.contains(".")) {
                    evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                    getToolkit().beep();
                    evt.consume();
                }
            }
            }
        }else{
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNuevoCostoProductoKeyTyped

    private void btnAtrasModificarProveedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasModificarProveedor1ActionPerformed
        jpnModificarProducto.setVisible(false);
        jpnProductos.setVisible(true);
        DefaultTableModel modeloProductos=(DefaultTableModel) tblProductos.getModel();
        modeloProductos.setRowCount(0);
        limpiandoTxtProducto();
        txtProductosBuscar.requestFocus();
        
    }//GEN-LAST:event_btnAtrasModificarProveedor1ActionPerformed

    private void txtCantidadVenderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVenderKeyPressed
        char c=evt.getKeyChar();      
         
         
         if (c == (char) KeyEvent.VK_ENTER) {
             if(txtCantidadVender.getText().isEmpty()){
                 mensajeNotificacion("Ingrese una cantidad válida");
                 txtCantidadVender.requestFocus();
          txtCantidadVender.selectAll();
             }else{
                 tablaProductosVender();
             }
             
            
        }
    }//GEN-LAST:event_txtCantidadVenderKeyPressed

    private void tblProductosVenderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosVenderKeyPressed
        char c=evt.getKeyChar();      
         
         
         if (c == (char) KeyEvent.VK_DELETE) {
             eliminarProductos();
             
            
        }
    }//GEN-LAST:event_tblProductosVenderKeyPressed

    private void txtNombreProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProveedorActionPerformed
        txtTelefonoProveedor.requestFocus();
    }//GEN-LAST:event_txtNombreProveedorActionPerformed

    private void txtTelefonoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoProveedorActionPerformed
        txtDireccionProveedor.requestFocus();
    }//GEN-LAST:event_txtTelefonoProveedorActionPerformed

    private void txtDireccionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionProveedorActionPerformed
        txtNIT.requestFocus();
    }//GEN-LAST:event_txtDireccionProveedorActionPerformed

    private void txtNuevoNombreProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoNombreProveedorActionPerformed
        txtNuevoTelefono.requestFocus();
    }//GEN-LAST:event_txtNuevoNombreProveedorActionPerformed

    private void txtNuevoTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoTelefonoActionPerformed
        txtNuevoDireccionProveedor.requestFocus();
    }//GEN-LAST:event_txtNuevoTelefonoActionPerformed

    private void txtNuevoDireccionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoDireccionProveedorActionPerformed
        txtNuevoNit.requestFocus();
    }//GEN-LAST:event_txtNuevoDireccionProveedorActionPerformed

    private void txtNomProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomProdKeyTyped
        // TODO add your handling code here:
         char ch = evt.getKeyChar();               

        if (ch == (char) KeyEvent.VK_ENTER) {
        txtCantidad.requestFocus();
        txtCantidad.selectAll();
        
        }
         int c=(int) evt.getKeyChar();
         char mayu=evt.getKeyChar();
         
         if ((c>=65 && c<=90) || (c>=97 && c<=122)  || (c==32) || (c >=48 && c<=57) || (c== (char)KeyEvent.VK_BACK_SPACE) || (c== (char)KeyEvent.VK_ENTER)) {
             if (Character.isLowerCase(mayu)) {
                 String cadena=(""+mayu).toUpperCase();
                 mayu=cadena.charAt(0);
                 evt.setKeyChar(mayu);
             }
        }else{
             evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            getToolkit().beep();
            evt.consume();
         }
         
        
    }//GEN-LAST:event_txtNomProdKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        s = evt.getKeyChar();
        if (!Character.isDigit(s)) {
            getToolkit().beep();
            evt.consume();

        }
        if (s == KeyEvent.VK_ENTER && Integer.parseInt(txtCantidad.getText())>0) {
                
            txtCostoProd.requestFocus();
        }
          
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        try {
            guardarVenta();
        } catch (ErrorTienda ex) {
            Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_btnVenderActionPerformed

    private void txtCodBarraProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodBarraProductosActionPerformed
        txtNombreProductos.requestFocus();
    }//GEN-LAST:event_txtCodBarraProductosActionPerformed

    private void txtNombreProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductosActionPerformed
        txtInventarioProducto.requestFocus();
    }//GEN-LAST:event_txtNombreProductosActionPerformed

    private void txtInventarioProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInventarioProductoActionPerformed
        txtCostoProductos.requestFocus();
    }//GEN-LAST:event_txtInventarioProductoActionPerformed

    private void txtNuevoNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoNombreProductoActionPerformed
        txtNuevoInventarioProducto.requestFocus();
        if (txtNuevoInventarioProducto.isFocusable()) {
            txtNuevoInventarioProducto.selectAll();
        }
{
            
        }

    }//GEN-LAST:event_txtNuevoNombreProductoActionPerformed

    private void txtNuevoInventarioProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoInventarioProductoActionPerformed
        txtNuevoCostoProducto.requestFocus();
        if (txtNuevoCostoProducto.isFocusable()) {
            txtNuevoCostoProducto.selectAll();
        }
    }//GEN-LAST:event_txtNuevoInventarioProductoActionPerformed

    private void btnCancelarVentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarVentaMouseEntered
        btnCancelarVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/cancelarB.png")));
    }//GEN-LAST:event_btnCancelarVentaMouseEntered

    private void btnCancelarVentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarVentaMouseExited
        btnCancelarVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/cancelar.png")));
    }//GEN-LAST:event_btnCancelarVentaMouseExited

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ArrayList<Proveedor> Proveedor = new ArrayList();
        Object IdProveedor;
        Object[] llenarProveedor = new Object[4];
        ArrayList<DetalleCompra> Articulos = new ArrayList();
        DetalleCompra detalleCompra = new DetalleCompra();
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Compra compra = new Compra();
        ControladorProducto producto = new ControladorProducto();
        Proveedor proveedor = new Proveedor();
        try {
            Proveedor = ControladorProveedor.Buscar(cmbProveedor.getSelectedItem().toString());
            Iterator<Proveedor> prov = Proveedor.iterator();
            IdProveedor = prov.next();                       
            if (tblCompra.getRowCount()>0) {
               String IDprov=IdProveedor.toString();
                for (int i = 0; i < tblCompra.getRowCount(); i++) {
                    detalleCompra.setCostoUnitario(Double.parseDouble(decimal.format(Double.parseDouble(tblCompra.getValueAt(i, 3).toString()))));
                    detalleCompra.setCantidad(Integer.parseInt(tblCompra.getValueAt(i, 2).toString()));
                    detalleCompra.setPRODUCTO(ControladorProducto.Obtener(tblCompra.getValueAt(i, 0).toString()));
                    Articulos.add(detalleCompra);
                }
                while (prov.hasNext()) {
                    llenarProveedor[0] = prov.next();
                    llenarProveedor[1] = prov.next();
                    llenarProveedor[2] = prov.next();
                    llenarProveedor[3] = prov.next();
                    
                    
                }    
                    proveedor.setIdProveedor(Integer.parseInt(IdProveedor.toString()));
                    proveedor.setNombre(llenarProveedor[0].toString());
                    proveedor.setTelefono(llenarProveedor[1].toString());
                    proveedor.setDireccion(llenarProveedor[2].toString());
                    proveedor.setNIT(llenarProveedor[3].toString());
                    
                String total="";
                
                for (int j = 1; j < txtTotal.getText().length(); j++) {
                    total = total + txtTotal.getText().charAt(j);
                }
 
               compra.setIdCompra(Integer.parseInt(txtIdCompra.getText()));
               compra.setPROVEEDOR(proveedor);
               compra.setFecha(formato.format(fechaActual));
               compra.setARTICULOS(Articulos);
               compra.setTotal(Double.parseDouble(total));
               
               Object [][] detallesCompra;
            
            int filas = tablaModel.getRowCount();
            detallesCompra = new Object[filas][4];
            for(int x=0;x<filas;x++){
                detallesCompra[x][0]=tablaModel.getValueAt(x, 0);
                detallesCompra[x][1]=Integer.parseInt(txtIdCompra.getText());
                detallesCompra[x][2]=Integer.parseInt(String.valueOf(tablaModel.getValueAt(x, 2)));
                detallesCompra[x][3]=Double.parseDouble(String.valueOf(tablaModel.getValueAt(x, 3)));
            }
               ControladorCompra.Agregar(compra, detallesCompra);
               ControladorCompra.ActualizarPrecioPromedioProducto(detallesCompra);
               ControladorCompra.ActualizarInventario(detallesCompra);

               JOptionPane.showMessageDialog(rootPane, "Compra agregada con exito");
            }
            
            int idCompra;
            idCompra = ControladorCompra.ObtenerIdCompra();
            limpiarCompra();
            txtIdCompra.setText(String.valueOf(idCompra+1));
            tablaModel.setNumRows(0);
            txtTotal.setText("$");
            
        } catch (ErrorTienda ex) {
            Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtProductosBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductosBuscarKeyPressed
        char c=evt.getKeyChar();    
        if (c == (char) KeyEvent.VK_ENTER) {
            tablaBuscarProductos();
        }
    }//GEN-LAST:event_txtProductosBuscarKeyPressed

    private void lbl7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl7MouseEntered
        pnlPortada.setVisible(true);
    }//GEN-LAST:event_lbl7MouseEntered

    private void lbl7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl7MouseExited
        pnlPortada.setVisible(false);
    }//GEN-LAST:event_lbl7MouseExited

    private void btnBuscarProductoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoVentaActionPerformed
        frmBUscarVentas.show();
        frmBUscarVentas.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnBuscarProductoVentaActionPerformed

    private void txtBuscarProductoVenderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoVenderKeyPressed
        char c=evt.getKeyChar();

        if (c == (char) KeyEvent.VK_ENTER) {
            
            String nombre=txtBuscarProductoVender.getText();
            modeloProductos.setRowCount(0);
            ArrayList<Producto> productos = new ArrayList();
            Object[] fila = new Object[4];
            if (nombre.equals("")) {
                JOptionPane.showMessageDialog(null, "No ha introducido el codigo de barra o el nombre");
            }else{
                String[] campos = new String[] {"CodBarra", "Nombre", "Inventario", "Costo $"};
                try {

                    productos = ControladorProducto.Buscar(nombre);
                    modeloProductos.setColumnIdentifiers(campos);
                    //decimal.setRoundingMode(RoundingMode.CEILING); 
                    Iterator<Producto> prod = productos.iterator();
                    while (prod.hasNext()) {
                        fila[0] = prod.next();
                        fila[1] = prod.next();
                        fila[2] = prod.next();
                        fila[3] = decimal.format(columnaprecio(Double.parseDouble(String.valueOf(prod.next()))));
                        modeloProductos.addRow(fila);
                        tblBuscarProductosVender.setModel(modeloProductos);

                    }
                } catch (ErrorTienda ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            }

        }
    }//GEN-LAST:event_txtBuscarProductoVenderKeyPressed

    public double columnaprecio(double precio){
        double resultado=0;
        resultado = precio/(0.85);
        return resultado;
    }
    private void btnBuscarAgregarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAgregarVentaActionPerformed
        try {
            AsignarCodBarra();
            frmBUscarVentas.dispose();
        } catch (ErrorTienda ex) {
            Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarAgregarVentaActionPerformed

    private void lblCerrarVentasBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarVentasBuscarMouseClicked
        frmBUscarVentas.dispose();
        modeloProductos.setRowCount(0);
        txtBuscarProductoVender.setText("");
    }//GEN-LAST:event_lblCerrarVentasBuscarMouseClicked

    private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaActionPerformed
        try {
            modeloTablaVender.setRowCount(0);
            limpiarVenta();
            txtTotalventa.setText("$0");
        } catch (ErrorTienda ex) {
            Logger.getLogger(JFRPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarVentaActionPerformed

    private void tblCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCompraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCompraKeyTyped

    private void tblCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCompraKeyPressed
        char c=evt.getKeyChar();      
        int iteraciones=0;
         
         if (c == (char) KeyEvent.VK_DELETE) {
             eliminarCompras();
             int filas = tablaModel.getRowCount();
            
            double total=0;
            while(iteraciones<filas){
               total+=Double.parseDouble(String.valueOf(tablaModel.getValueAt(iteraciones, 4)));
               iteraciones++;}
            
            double totalFinal=Double.parseDouble(decimal.format(total));
            txtTotal.setText("$"+totalFinal);
            
            
        }
    }//GEN-LAST:event_tblCompraKeyPressed

    private void btnCancelarVenta1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarVenta1MouseExited
        btnCancelarVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/cancelarB.png")));
    }//GEN-LAST:event_btnCancelarVenta1MouseExited

    private void btnCancelarVenta1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarVenta1MouseEntered
        btnCancelarVenta.setIcon(new ImageIcon(getClass().getResource("/iconos/cancelar.png")));
    }//GEN-LAST:event_btnCancelarVenta1MouseEntered

    private void btnCancelarVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVenta1ActionPerformed
        /*for(int i =0; i<tablaModel.getRowCount(); i++){
            tablaModel.removeRow(i);
        }*/
        tablaModel.setRowCount(0);
        //tblCompras.removeAll();
        txtCodBarraProd.setText("");
        txtNomProd.setText("");
        txtCantidad.setText("1");
        txtCostoProd.setText("");
        
    }//GEN-LAST:event_btnCancelarVenta1ActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        txtProductosBuscar.setText("");
        DefaultTableModel modeloProductos=(DefaultTableModel) tblProductos.getModel();
        modeloProductos.setRowCount(0);
        
    }//GEN-LAST:event_btnProductosActionPerformed

    private void txtNITKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNITKeyTyped
              char c = evt.getKeyChar();
    if(txtNIT.getText().length()>=14){
               evt.consume();
        }else{
        if (c < '0' || c > '9') {
            
                if (c != (char) KeyEvent.VK_BEGIN) {
                    if (c != (char) KeyEvent.VK_BACK_SPACE) {
                        if (c != (char) KeyEvent.VK_DELETE) {
                            if (c != (char) KeyEvent.VK_ENTER) {
                            evt.consume();
                            JOptionPane.showMessageDialog(null, "Solo Numeros", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                }
        }   
    }
    }//GEN-LAST:event_txtNITKeyTyped

    private void txtNuevoNitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoNitKeyTyped
                      char c = evt.getKeyChar();
    if(txtNIT.getText().length()>=14){
               evt.consume();
        }else{
        if (c < '0' || c > '9') {
            
                if (c != (char) KeyEvent.VK_BEGIN) {
                    if (c != (char) KeyEvent.VK_BACK_SPACE) {
                        if (c != (char) KeyEvent.VK_DELETE) {
                            if (c != (char) KeyEvent.VK_ENTER) {
                            evt.consume();
                            JOptionPane.showMessageDialog(null, "Solo Numeros", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                }
        }   
    }
    }//GEN-LAST:event_txtNuevoNitKeyTyped

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        txtCodigoBarraVender.setText("");
        txtNombreProductoVender.setText("");
        txtCantidadVender.setText("1");
        txtTotalventa.setText("");
        DefaultTableModel modeloVentas=(DefaultTableModel) tblProductosVender.getModel();
        modeloVentas.setRowCount(0);
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed
        txtCodBarraProd.setText("");
        txtNomProd.setText("");
        txtCantidad.setText("1");
        txtCostoProd.setText("");
        txtTotal.setText("");
        txtNomProd.setEditable(false);
        DefaultTableModel modeloCompras=(DefaultTableModel) tblCompra.getModel();
        modeloCompras.setRowCount(0);
    }//GEN-LAST:event_btnComprasActionPerformed

    private void txtNuevoNombreProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNuevoNombreProductoMouseClicked
        contandoClicksIneventario=contandoClicksCosto=0;
        contandoClicksNombre += evt.getClickCount();
        if (txtNuevoNombreProducto.isFocusable()) {
            
            if (contandoClicksNombre==1) {
                txtNuevoNombreProducto.selectAll();
            }
        }
        
    }//GEN-LAST:event_txtNuevoNombreProductoMouseClicked

    private void txtNuevoInventarioProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNuevoInventarioProductoMouseClicked
        contandoClicksNombre=contandoClicksCosto=0;
        contandoClicksIneventario += evt.getClickCount();
        if (txtNuevoInventarioProducto.isFocusable()) {
            if (contandoClicksIneventario==1) {
                txtNuevoInventarioProducto.selectAll();
            }
        }
    }//GEN-LAST:event_txtNuevoInventarioProductoMouseClicked

    private void txtNuevoCostoProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNuevoCostoProductoMouseClicked
        contandoClicksNombre=contandoClicksIneventario=0;
        contandoClicksCosto +=evt.getClickCount();
        if (txtNuevoCostoProducto.isFocusable()) {
            if (contandoClicksCosto==1) {
                txtNuevoCostoProducto.selectAll();
            }
        }
    }//GEN-LAST:event_txtNuevoCostoProductoMouseClicked

                                                                                                                                                                                                                              
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFRPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFRPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFRPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFRPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JFRPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCompra;
    private javax.swing.JButton btnAgregarNuevoProducto;
    private javax.swing.JButton btnAgregarProductoVenta;
    private javax.swing.JButton btnAgregarProveedor;
    private javax.swing.JButton btnAtrasDetalleCompra;
    private javax.swing.JButton btnAtrasModificarProveedor;
    private javax.swing.JButton btnAtrasModificarProveedor1;
    private javax.swing.JButton btnAtrasProveedores;
    private javax.swing.JButton btnBuscarAgregarVenta;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnBuscarProductoVenta;
    private javax.swing.JButton btnCancelarVenta;
    private javax.swing.JButton btnCancelarVenta1;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarModificarProveedor;
    private javax.swing.JButton btnGuardarModificarProveedor1;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JLabel btnHome;
    private javax.swing.JButton btnModificarProducto;
    private javax.swing.JButton btnModificarProveedor;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnSalirProductos;
    private javax.swing.JButton btnVender;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.ButtonGroup btngFiltroProductos;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JFrame frmBUscarVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JSeparator jSeparator38;
    private javax.swing.JSeparator jSeparator39;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator40;
    private javax.swing.JSeparator jSeparator41;
    private javax.swing.JSeparator jSeparator42;
    private javax.swing.JSeparator jSeparator43;
    private javax.swing.JSeparator jSeparator44;
    private javax.swing.JSeparator jSeparator45;
    private javax.swing.JSeparator jSeparator46;
    private javax.swing.JSeparator jSeparator47;
    private javax.swing.JSeparator jSeparator48;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jpnAgregarProv;
    private javax.swing.JPanel jpnBarraMenu;
    private javax.swing.JPanel jpnBarraSuperior;
    private javax.swing.JPanel jpnCompras;
    private javax.swing.JPanel jpnCuarto;
    private javax.swing.JPanel jpnDetalleCompra;
    private javax.swing.JPanel jpnModificarProducto;
    private javax.swing.JPanel jpnModificarProveedor;
    private javax.swing.JPanel jpnNuevoProducto;
    private javax.swing.JPanel jpnPrimero;
    private javax.swing.JPanel jpnPrincipal;
    private javax.swing.JPanel jpnProductos;
    private javax.swing.JPanel jpnProveedores;
    private javax.swing.JPanel jpnQuinto;
    private javax.swing.JPanel jpnRegistroCompra;
    private javax.swing.JPanel jpnSegundo;
    private javax.swing.JPanel jpnSubMenu;
    private javax.swing.JPanel jpnTercero;
    public static javax.swing.JPanel jpnVentas;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lbl12;
    private javax.swing.JLabel lbl13;
    private javax.swing.JLabel lbl14;
    private javax.swing.JLabel lbl15;
    private javax.swing.JLabel lbl21;
    private javax.swing.JLabel lbl22;
    private javax.swing.JLabel lbl23;
    private javax.swing.JLabel lbl24;
    private javax.swing.JLabel lbl25;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl31;
    private javax.swing.JLabel lbl32;
    private javax.swing.JLabel lbl33;
    private javax.swing.JLabel lbl34;
    private javax.swing.JLabel lbl35;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl41;
    private javax.swing.JLabel lbl42;
    private javax.swing.JLabel lbl43;
    private javax.swing.JLabel lbl44;
    private javax.swing.JLabel lbl45;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lblBotonCerrar;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCerrarVentasBuscar;
    private javax.swing.JLabel lblCodBarraProd;
    private javax.swing.JLabel lblCostoProd;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIdCompra;
    private javax.swing.JLabel lblListadoCompras;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblMitad;
    private javax.swing.JLabel lblMitad2;
    private javax.swing.JLabel lblMitad3;
    private javax.swing.JLabel lblMitad4;
    private javax.swing.JLabel lblMitad5;
    private javax.swing.JLabel lblNomProd;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblProveedores3;
    private javax.swing.JLabel lblProveedores4;
    private javax.swing.JLabel lblProveedores5;
    private javax.swing.JLabel lblProveedores6;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlPortada;
    private javax.swing.JTable tblBuscarProductosVender;
    private javax.swing.JTable tblCompra;
    private javax.swing.JTable tblCompras;
    private javax.swing.JTable tblDetalleCompra;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProductosVender;
    private javax.swing.JTable tblProveedores;
    private javax.swing.JTextField txtBuscarProductoVender;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidadVender;
    private javax.swing.JTextField txtClienteVenta;
    private javax.swing.JTextField txtCodBarraProd;
    private javax.swing.JTextField txtCodBarraProductos;
    private javax.swing.JTextField txtCodBarraProductos1;
    public static javax.swing.JTextField txtCodigoBarraVender;
    private javax.swing.JTextField txtCostoProd;
    private javax.swing.JTextField txtCostoProductos;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIDProveedor;
    private javax.swing.JTextField txtIDProveedor1;
    private javax.swing.JTextField txtIdCompra;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtInventarioProducto;
    private javax.swing.JTextField txtNIT;
    private javax.swing.JTextField txtNomProd;
    private javax.swing.JTextField txtNombreProductoVender;
    private javax.swing.JTextField txtNombreProductos;
    private javax.swing.JTextField txtNombreProductos1;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNuevoCodBarraProducto;
    private javax.swing.JTextField txtNuevoCostoProducto;
    private javax.swing.JTextField txtNuevoDireccionProveedor;
    private javax.swing.JTextField txtNuevoInventarioProducto;
    private javax.swing.JTextField txtNuevoNit;
    private javax.swing.JTextField txtNuevoNombreProducto;
    private javax.swing.JTextField txtNuevoNombreProveedor;
    private javax.swing.JFormattedTextField txtNuevoTelefono;
    private javax.swing.JTextField txtPrecioProductos2;
    private javax.swing.JTextField txtProductosBuscar;
    private javax.swing.JTextField txtProveedoresBuscar;
    private javax.swing.JFormattedTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotal2;
    private javax.swing.JTextField txtTotalventa;
    // End of variables declaration//GEN-END:variables

    private void setVisible(JPopupMenu MenuEmergente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
