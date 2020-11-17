package Vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Entidades.*;
import Modelo.*;

public class JIFNotas extends javax.swing.JInternalFrame {
    private Alumno al;
    private Conexion con;
    private AlumnoData adt;
    private Materia mat;
    private InscripcionData indta;
    private DefaultTableModel modelo;
    private List<Inscripcion> listaIns;
    public JIFNotas() {
        initComponents();
        con = new Conexion();
        indta = new InscripcionData(con);
        adt = new AlumnoData(con);
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==2){
                    return true;
                }
                return false;
            }
            
        };
        cargarAlumnos();
        armarCabecera();
        jBGuardar.setEnabled(false);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCBAlumnos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTNotas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jBSalir = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();

        jCBAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBAlumnosActionPerformed(evt);
            }
        });

        jTNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTNotas);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Actualizar Notas");

        jBSalir.setBackground(new java.awt.Color(204, 0, 0));
        jBSalir.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jBSalir.setForeground(new java.awt.Color(255, 255, 255));
        jBSalir.setText("X");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jBGuardar.setText("Guardar cambios");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jBSalir)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jBGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBSalir))
                .addGap(18, 18, 18)
                .addComponent(jCBAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBGuardar)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        // TODO add your handling code here:
        int opcion = JOptionPane.showConfirmDialog(null, "Realmente desea salir?", "Confirmar salida", JOptionPane.CLOSED_OPTION, JOptionPane.CANCEL_OPTION);
        if(opcion==0){
            dispose();
        }
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jCBAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBAlumnosActionPerformed
        // TODO add your handling code here:
        borrarFilas();
        if(jCBAlumnos.getSelectedItem()!=null){
            cargarDatos();
            jBGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_jCBAlumnosActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        // TODO add your handling code here:
        try{ 
            int fila = (Integer)jTNotas.getSelectedRow();
        if(jCBAlumnos.getSelectedItem()!=null){
            al = (Alumno)jCBAlumnos.getSelectedItem();
            //int id= (Integer)modelo.getValueAt(fila, 0);
            mat = (Materia)modelo.getValueAt(fila, 1);
            float nota = Float.valueOf(modelo.getValueAt(fila, 2).toString());
            
            indta.actualizarNotaCursada(al.getIdAlumno(), mat.getIdMateria(), nota);
            borrarFilas();
        }
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(this, "No hay cambios para guardar.");
        }
    }//GEN-LAST:event_jBGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JComboBox<Alumno> jCBAlumnos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTNotas;
    // End of variables declaration//GEN-END:variables
    
    private void cargarAlumnos(){
       ArrayList<Alumno> lista = adt.obtenerAlumnos();
       for(Alumno item: lista){
           jCBAlumnos.addItem(item);
       }
    }
    private void armarCabecera(){
        ArrayList<Object> columnas = new ArrayList<>();
        
        columnas.add("ID");
        columnas.add("Materia");
        columnas.add("Nota");
        for(Object it:columnas){
        
            modelo.addColumn(it);
        }
        jTNotas.setModel(modelo);
        
    }
    private void borrarFilas(){
        int a = modelo.getRowCount()-1;
        for (int i =a; i>=0; i--){
            modelo.removeRow(i);
        }
    
    }
    private void cargarDatos(){
        borrarFilas();
        Alumno alumno = (Alumno)jCBAlumnos.getSelectedItem();
        listaIns = indta.buscarInscripcionPorAlumno(alumno.getIdAlumno());
        for(Inscripcion i: listaIns){
            modelo.addRow(new Object[]{i.getIdInscripcion(),i.getMateria(), i.getNota()});
        }
    }
}
