package com.pruebas.awakelab.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Producto {

    @Id
    @SequenceGenerator(name = "secPro", sequenceName="SECPRO", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secPro")
    private Integer id;

    @Column( nullable = false)
    @NotEmpty(message = "Indique el nombre del producto")
    private String nombre;

    @Column(nullable = false)
    @NotNull(message = "Indique el Stock del Producto")
    private Integer stock;

    @Column(nullable = false)
    private String rutaFoto;

    @Column(nullable = false)
    @NotNull(message = "Indique el Precio ")
    private Float precio;

    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "id_marca", foreignKey = @ForeignKey(name = "Fk_producto_marca"))
    @NotNull(message = "Debe Seleccionar Una Marca")
    private Marca marca;

    @PrePersist
    public void prePersist(){
        this.fechaIngreso = new Date();
    }

}
