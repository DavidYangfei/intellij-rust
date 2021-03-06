package org.rust.lang.core.stubs.elements


import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.stubs.StubInputStream
import com.intellij.psi.stubs.StubOutputStream
import com.intellij.util.io.StringRef
import org.rust.lang.core.psi.RustImplMethodMemberElement
import org.rust.lang.core.psi.impl.RustImplMethodMemberElementImpl
import org.rust.lang.core.stubs.RustFnElementStub
import org.rust.lang.core.stubs.RustNamedStubElementType

object RustImplMethodMemberStubElementType : RustNamedStubElementType<RustImplMethodMemberElementStub, RustImplMethodMemberElement>("IMPL_METHOD_MEMBER") {
    override fun createStub(psi: RustImplMethodMemberElement, parentStub: StubElement<*>?): RustImplMethodMemberElementStub =
        RustImplMethodMemberElementStub(parentStub, this,
            psi.name, psi.isPublic, psi.fnAttributes)

    override fun createPsi(stub: RustImplMethodMemberElementStub): RustImplMethodMemberElement =
        RustImplMethodMemberElementImpl(stub, this)

    override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): RustImplMethodMemberElementStub =
        RustImplMethodMemberElementStub(parentStub, this,
            dataStream.readName(), dataStream.readBoolean(), dataStream.readFnAttributes())

    override fun serialize(stub: RustImplMethodMemberElementStub, dataStream: StubOutputStream) = with(dataStream) {
        writeName(stub.name)
        writeBoolean(stub.isPublic)
        writeFnAttributes(stub.attributes)
    }
}


class RustImplMethodMemberElementStub : RustFnElementStub<RustImplMethodMemberElement> {
    constructor(parent: StubElement<*>?, elementType: IStubElementType<*, *>,
                name: StringRef?, isPublic: Boolean, attributes: FnAttributes)
    : super(parent, elementType, name ?: StringRef.fromNullableString(""), isPublic, attributes)

    constructor(parent: StubElement<*>?, elementType: IStubElementType<*, *>,
                name: String?, isPublic: Boolean, attributes: FnAttributes)
    : super(parent, elementType, name ?: "", isPublic, attributes)
}
