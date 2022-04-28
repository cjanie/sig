package com.android.sig.businesslogic.enums

enum class TypeEnum {

    RUIN {
            override fun <E> accept(visitor: TypeVisitor<E>): E {
                return visitor.visitRuin()
            }
         },
    CASTEL {
            override fun <E> accept(visitor: TypeVisitor<E>): E {
                return visitor.visitCastel()
            }
           },
    WALL {
            override fun <E> accept(visitor: TypeVisitor<E>): E {
                return visitor.visitWall()
            }

         },
    HISTORIC_SITE {
                    override fun <E> accept(visitor: TypeVisitor<E>): E {
                        return visitor.visitHistoricSite()
                    }
                  },
    ARCHEOLOGIC_SITE {
                        override fun <E> accept(visitor: TypeVisitor<E>): E {
                            return visitor.visitArchaeologicSite()
                        }
                     },
    OTHER_TYPE {
                override fun <E> accept(visitor: TypeVisitor<E>): E {
                    return visitor.visitOtherType()
                }
               };

    abstract fun <E>  accept(visitor: TypeVisitor<E>): E
}
