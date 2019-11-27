/*
 * IProperty.h
 *
 *  Created on: 29.04.2018
 *      Author: kuhn, wendel
 */
#ifndef API_IPROPERTY_H_
#define API_IPROPERTY_H_

#include "submodel/api/submodelelement/IDataElement.h"
#include "submodel/api/submodelelement/IDataElement.h"
#include "basyx/types.h"

namespace basyx {
namespace aas {
namespace submodelelement {
namespace property {

enum PropertyType
{
  Single, Collection, Map, Container
};

namespace PropertyPaths
{
  static constexpr long serialVersionUID = 1L;
  static constexpr char VALUE[] = "value";
  static constexpr char VALUEID[] = "valueId";
  static constexpr char VALUETYPE[] = "valueType";
}

/* *********************************************************************************
 * Property interface
 * *********************************************************************************/
class IProperty
{

public:
  virtual PropertyType getPropertyType() const = 0;

  virtual void setValue(const basyx::object & obj) = 0;
  virtual basyx::object getValue() const = 0;

  virtual void setValueId(const basyx::object & obj) = 0;
  virtual basyx::object getValueId() const = 0;

};

}
}
}
}

#endif /* API_IPROPERTY_H_ */