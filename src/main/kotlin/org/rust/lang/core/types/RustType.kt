package org.rust.lang.core.types

import org.rust.lang.core.psi.RustImplItemElement
import org.rust.lang.core.psi.RustTraitItemElement
import org.rust.lang.core.types.visitors.RustTypeVisitor

interface RustType {

    fun <T> accept(visitor: RustTypeVisitor<T>): T

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    override fun toString(): String

    /**
     * Impls without traits, like `impl S { ... }`
     *
     * You don't need to import such impl to be able to use its methods.
     * There may be several `impl` blocks for the same type and they may
     * be spread across different files and modules (we don't handle this yet)
     */
    val inherentImpls: Collection<RustImplItemElement> get() = emptyList()

    fun implFor(trait: RustTraitItemElement): RustImplItemElement? = null

}
